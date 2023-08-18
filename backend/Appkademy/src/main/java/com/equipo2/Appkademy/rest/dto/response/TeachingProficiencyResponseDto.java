package com.equipo2.Appkademy.rest.dto.response;

import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeachingProficiencyResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6017525869803988353L;

    private TeachingMasteryLevel masteryLevel;
    private String subject;

}
