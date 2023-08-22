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
public class RegisterRequestDto {

    @Schema(title = "email", example = "example@gmail.com")
    private String email;

    @Schema(title = "password", example = "123456789")
    private String password;

}
