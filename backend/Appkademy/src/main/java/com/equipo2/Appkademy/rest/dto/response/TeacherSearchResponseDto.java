package com.equipo2.Appkademy.rest.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TeacherSearchResponseDto extends PageResponseDto<TeacherCompactResponseDto> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6489567417377310538L;

}
