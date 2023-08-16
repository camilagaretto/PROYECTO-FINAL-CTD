package com.equipo2.Appkademy.rest.dto.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TeacherSignupRequestCreateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7251864436361794404L;

    @Valid
    private TeacherCreateRequestDto teacherFormData;

}
