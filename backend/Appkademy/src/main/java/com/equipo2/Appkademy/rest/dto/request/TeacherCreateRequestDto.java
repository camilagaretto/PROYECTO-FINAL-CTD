package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(title = "User id", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = ErrorCodes.USER_ID_CANNOT_BE_NULL)
    private Long userId;

    @Schema(title = "Teacher name", example = "Juan Martin", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String firstName;

    @Schema(title = "Teacher last name", example = "Rodriguez", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.LAST_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String lastName;

    @Schema(title = "email", example = "juan.martin@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.EMAIL_CANNOT_BE_NULL_OR_EMPTY)
    private String email;

    @Schema(title = "Short description", example = "Let's learn math together!",
            requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 100)
    @NotBlank(message = ErrorCodes.SHORT_DESCRIPTION_CANNOT_BE_NULL_OR_EMPTY)
    private String shortDescription;

    @Schema(title = "Full description (~64k UTF-8 characters)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.FULL_DESCRIPTION_CANNOT_BE_NULL_OR_EMPTY)
    private String fullDescription;

    @Schema(title = "Address", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    @NotNull(message = ErrorCodes.ADDRESS_CANNOT_BE_NULL)
    private AddressCreateRequestDto address;

    @Schema(title = "Hourly rates charged by teacher", example = "{ \"ARS\" : \"2500\"}", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = ErrorCodes.HOURLY_RATES_CANNOT_BE_NULL_OR_EMPTY)
    private Map<Currency, BigDecimal> hourlyRates;

    @Schema(title = "If classes can be taught in person, remotely or both", example = "{ \"FACE_TO_FACE\" : \"true\"}",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = ErrorCodes.MODALITIES_CANNOT_BE_NULL_OR_EMPTY)
    private Map<Modality, Boolean> modalities;

    @Schema(title = "Teaching subjects and their respective level", example = "[{ \"subject\" : \"MATH\", \"masteryLevel\" : \"HIGHSCHOOL\"}]", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    @NotEmpty(message = ErrorCodes.PROFICIENCIES_CANNOT_BE_NULL_OR_EMPTY)
    private List<TeachingProficiencyDto> proficiencies;

    @Schema(title = "Weekly working schedule", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    @NotNull(message = ErrorCodes.WEEKLY_WORKING_SCHEDULE_CANNOT_BE_NULL)
    private WeeklyWorkingScheduleCreateRequestDto weeklyWorkingSchedule;

    @Schema(title = "Teacher scheduled appointments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Valid
    private List<ScheduledAppointmentCreateRequestDto> scheduledAppointments;

    @Schema(title = "Total number of likes given to professor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long totalLikes;

    @Schema(title = "Link to professor profile picture", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String profilePictureUrl;

    @Schema(title = "List of characteristics", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<CharacteristicCreateRequestDto> characteristics;

}
