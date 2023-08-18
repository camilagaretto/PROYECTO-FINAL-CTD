package com.equipo2.Appkademy.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {

    @Schema(title = "email", example = "superAdminUser@gmail.com")
    private String email;

    @Schema(title = "password", example = "thePassword")
    private String password;

}
