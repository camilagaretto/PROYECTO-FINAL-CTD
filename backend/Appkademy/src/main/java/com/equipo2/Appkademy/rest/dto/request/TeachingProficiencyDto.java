package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import com.equipo2.Appkademy.core.model.enums.TeachingSubject;
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


    private TeachingMasteryLevel masteryLevel;

    private TeachingSubject subject;

}
