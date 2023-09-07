package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.security.model.PermissionConstants;
import com.equipo2.Appkademy.core.security.service.AuthenticationService;
import com.equipo2.Appkademy.rest.controller.documentation.IAuthenticationContoller;
import com.equipo2.Appkademy.rest.dto.request.AuthenticationRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RegisterRequestDto;
import com.equipo2.Appkademy.rest.dto.request.RoleRequestDto;
import com.equipo2.Appkademy.rest.dto.response.AuthenticationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements IAuthenticationContoller {

    private final AuthenticationService authenticationService;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request){
        AuthenticationResponseDto responseDto = authenticationService.register(request);
        return new ResponseEntity<AuthenticationResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Override
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('" + PermissionConstants.ROLE_UPDATE + "')")
    public ResponseEntity<AuthenticationResponseDto> updateRoles(@PathVariable Long userId, @RequestBody RoleRequestDto request){
        return ResponseEntity.ok(authenticationService.updateRoles(userId, request));
    }
}
