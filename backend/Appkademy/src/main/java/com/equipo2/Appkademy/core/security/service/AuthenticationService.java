package com.equipo2.Appkademy.core.security.service;

import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.RoleRepository;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.rest.dto.request.AuthenticationRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RegisterRequestDto;
import com.equipo2.Appkademy.rest.dto.response.AuthenticationResponseDto;
import com.equipo2.Appkademy.rest.error.BusinessException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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

        if(repository.findByEmail(request.getEmail()).isPresent()){
            throw new BusinessException(ErrorCodes.EMAIL_ALREADY_REGISTERED);
        }
        
        if(request.getPassword().length() < 7){
            throw new BusinessException(ErrorCodes.MINIMUM_PASSSWORD_LENGTH_IS_7_CHARACTERS);
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(roleRepository.findByName("USER")))
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .isAdmin(false)
                .build();
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

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .isAdmin(isAdmin)
                .build();
    }
}
