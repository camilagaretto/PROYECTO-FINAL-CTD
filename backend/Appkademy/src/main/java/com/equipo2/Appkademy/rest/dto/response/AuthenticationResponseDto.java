
package com.equipo2.Appkademy.rest.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

    private Long userId;
    private String token;

    private Boolean isAdmin;

}
