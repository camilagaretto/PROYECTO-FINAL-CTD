package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6417087755664586128L;

    //TODO: validations?
    //TODO: Swagger annotations

    private String firstName;

    private String lastName;

    private String shortDescription;

    private String fullDescription;

    private AddressCreateRequestDto address;

    private Map<Currency, BigDecimal> hourlyRates;

    private Map<Modality, Boolean> modalities;

    private List<TeachingProficiencyCreateRequestDto> proficiencies;

    private WeeklyWorkingScheduleCreateRequestDto weeklyWorkingSchedule;

    private List<ScheduledAppointmentCreateRequestDto> scheduledAppointments;

    private Long totalLikes;

    private String profilePictureUrl;

}
