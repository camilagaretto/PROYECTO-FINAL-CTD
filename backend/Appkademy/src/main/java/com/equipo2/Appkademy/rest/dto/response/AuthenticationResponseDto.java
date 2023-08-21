
package com.equipo2.Appkademy.rest.dto.response;

import com.equipo2.Appkademy.core.model.enums.UserType;
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
    private UserType userType;
    private Long userTypeId;

}
