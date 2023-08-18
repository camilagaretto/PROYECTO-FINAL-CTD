package com.equipo2.Appkademy.core.security.service;

import com.equipo2.Appkademy.core.security.model.Role;
import com.equipo2.Appkademy.core.security.model.User;
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

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) {

        if(repository.findByEmail(request.getEmail()).isPresent()){
            throw new BusinessException(ErrorCodes.EMAIL_ALREADY_REGISTERED);
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .token(jwtToken)
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
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .build();
    }
}
