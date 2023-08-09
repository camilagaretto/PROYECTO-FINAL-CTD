package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import com.equipo2.Appkademy.core.model.enums.TeachingSubject;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TeachingProficiencyDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2571030330581238034L;

    @Parameter
    private TeachingMasteryLevel masteryLevel;

    @Parameter
    private TeachingSubject subject;

}
