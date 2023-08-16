package com.equipo2.Appkademy.rest.dto.response;

import com.equipo2.Appkademy.core.model.enums.ReviewDecision;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TeacherSignupRequestResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8840449846611121272L;

    private TeacherResponseDto teacher;
    private LocalDateTime requestCreatedOn;
    private boolean requestHasBeenReviewed;
    private ReviewDecision reviewDecision;

}
