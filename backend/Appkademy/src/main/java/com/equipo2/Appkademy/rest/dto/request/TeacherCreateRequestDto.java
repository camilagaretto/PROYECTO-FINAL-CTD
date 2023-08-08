package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    //TODO: Swagger annotations

    @NotBlank(message = ErrorCodes.FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String firstName;

    @NotBlank(message = ErrorCodes.LAST_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String lastName;

    @NotBlank(message = ErrorCodes.EMAIL_CANNOT_BE_NULL_OR_EMPTY)
    private String email;

    @NotBlank(message = ErrorCodes.SHORT_DESCRIPTION_CANNOT_BE_NULL_OR_EMPTY)
    private String shortDescription;

    @NotBlank(message = ErrorCodes.FULL_DESCRIPTION_CANNOT_BE_NULL_OR_EMPTY)
    private String fullDescription;

    @Valid
    @NotNull(message = ErrorCodes.ADDRESS_CANNOT_BE_NULL)
    private AddressCreateRequestDto address;

    @NotEmpty(message = ErrorCodes.HOURLY_RATES_CANNOT_BE_NULL_OR_EMPTY)
    private Map<Currency, BigDecimal> hourlyRates;

    @NotEmpty(message = ErrorCodes.MODALITIES_CANNOT_BE_NULL_OR_EMPTY)
    private Map<Modality, Boolean> modalities;

    @Valid
    @NotEmpty(message = ErrorCodes.PROFICIENCIES_CANNOT_BE_NULL_OR_EMPTY)
    private List<TeachingProficiencyCreateRequestDto> proficiencies;

    @Valid
    @NotNull(message = ErrorCodes.WEEKLY_WORKING_SCHEDULE_CANNOT_BE_NULL)
    private WeeklyWorkingScheduleCreateRequestDto weeklyWorkingSchedule;

    @Valid
    private List<ScheduledAppointmentCreateRequestDto> scheduledAppointments;

    private Long totalLikes;

    private String profilePictureUrl;

}
