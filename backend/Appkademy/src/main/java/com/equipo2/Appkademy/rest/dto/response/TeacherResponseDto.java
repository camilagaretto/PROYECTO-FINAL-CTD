package com.equipo2.Appkademy.rest.dto.response;

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
public class TeacherResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3107751513964284166L;

    private Long id;

    private Long providerCategoryId;

    private boolean identityVerified;

    private String firstName;

    private String lastName;

    private String shortDescription;

    private String fullDescription;

    private AddressResponseDto address;

    private Map<Currency, BigDecimal> hourlyRates;

    private Map<Modality, Boolean> modalities;

    private List<TeachingProficiencyResponseDto> proficiencies;

    private WeeklyWorkingScheduleResponseDto weeklyWorkingSchedule;

    private List<ScheduledAppointmentResponseDto> scheduledAppointments;

    private Long totalLikes;

    private String profilePictureUrl;

    private boolean enabled;

    private boolean signupApprovedByAdmin;

    private List<CharacteristicResponseDto> characteristics;

}
