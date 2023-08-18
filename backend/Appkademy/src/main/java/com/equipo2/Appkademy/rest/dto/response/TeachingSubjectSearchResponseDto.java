package com.equipo2.Appkademy.rest.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TeachingSubjectSearchResponseDto extends PageResponseDto<TeachingSubjectResponseDto> implements Serializable {

    @Serial
    private static final long serialVersionUID = 6102429719303308310L;

}
