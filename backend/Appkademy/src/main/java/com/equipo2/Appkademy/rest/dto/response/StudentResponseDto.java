package com.equipo2.Appkademy.rest.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8798777604918502026L;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private boolean enabled;

    private List<ScheduledAppointmentResponseDto> scheduledAppointments;

    private List<Long> likedProviderIds;

    private AddressResponseDto address;

}
