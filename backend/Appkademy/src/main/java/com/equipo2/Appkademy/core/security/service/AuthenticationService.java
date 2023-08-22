package com.equipo2.Appkademy.core.security.service;

import com.equipo2.Appkademy.core.model.entity.BaseSqlEntity;
import com.equipo2.Appkademy.core.security.model.Role;
import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.RoleRepository;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.rest.dto.request.AuthenticationRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RegisterRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RoleRequestDto;
import com.equipo2.Appkademy.rest.dto.response.AuthenticationResponseDto;
import com.equipo2.Appkademy.rest.error.BadRequestException;
import com.equipo2.Appkademy.rest.error.BusinessException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN";


    public AuthenticationResponseDto register(RegisterRequestDto request) {
        assertEmailIsValid(request.getEmail());
        assertEmailDoesntAlreadyExist(request.getEmail());
        
        if(request.getPassword().length() < 7){
            throw new BusinessException(ErrorCodes.MINIMUM_PASSSWORD_LENGTH_IS_7_CHARACTERS);
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(roleRepository.findByName("USER")))
                .build();

        repository.save(user);

        Set<Long> roleIds = user.getRoles().stream().map(BaseSqlEntity::getId).collect(Collectors.toSet());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .isAdmin(false)
                .roleIds(roleIds)
                .email(user.getEmail())
                .build();
    }

    private void assertEmailDoesntAlreadyExist(String email) {
        if(repository.findByEmail(email).isPresent()){
            throw new BusinessException(ErrorCodes.EMAIL_ALREADY_REGISTERED);
        }
    }

    public void assertEmailIsValid(String email) {
        if(!EmailValidator.getInstance().isValid(email)){
            throw new BadRequestException("email", email);
        };
    }


    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //if I get to this point the user is authenticated -> username and password are correct
        var user = repository.findByEmail(request.getEmail()).orElseThrow();

        //Check if user has role admin or super admin
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals(ADMIN_ROLE) || role.getName().equals(SUPER_ADMIN_ROLE));

        Set<Long> roleIds = user.getRoles().stream().map(BaseSqlEntity::getId).collect(Collectors.toSet());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .isAdmin(isAdmin)
                .userType(user.getType())
                .userTypeId(user.getUserTypeId())
                .roleIds(roleIds)
                .email(user.getEmail())
                .build();
    }

    public AuthenticationResponseDto updateRoles(Long userId, RoleRequestDto request) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCodes.USER_NOT_FOUND));
        List<Role> rolesFromRequest = roleRepository.findAllById(request.getRoleIds());
        user.setRoles(new HashSet<>(rolesFromRequest));
        repository.save(user);

        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals(ADMIN_ROLE) || role.getName().equals(SUPER_ADMIN_ROLE));

        Set<Long> roleIds = user.getRoles().stream().map(BaseSqlEntity::getId).collect(Collectors.toSet());

        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .isAdmin(isAdmin)
                .userType(user.getType())
                .userTypeId(user.getUserTypeId())
                .roleIds(roleIds)
                .build();
    }
}
