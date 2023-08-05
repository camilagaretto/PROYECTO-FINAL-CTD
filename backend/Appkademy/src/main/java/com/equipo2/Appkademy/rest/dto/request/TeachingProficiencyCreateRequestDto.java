package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import com.equipo2.Appkademy.core.model.enums.TeachingSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeachingProficiencyCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4174670209866381250L;

    private TeachingMasteryLevel masteryLevel;
    private TeachingSubject subject;

}
