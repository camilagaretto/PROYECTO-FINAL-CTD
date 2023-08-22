package com.equipo2.Appkademy.rest.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StudentSearchResponseDto extends PageResponseDto<StudentResponseDto> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3905433562990061786L;
}
