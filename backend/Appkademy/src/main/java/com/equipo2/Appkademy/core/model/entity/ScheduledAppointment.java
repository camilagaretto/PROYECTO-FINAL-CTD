package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "scheduled_appointment")
public class ScheduledAppointment extends BaseSqlEntity<Long>{

    @Column(name = "starts_on", nullable = false)
    private LocalDateTime startsOn;

    @Column(name = "ends_on", nullable = false)
    private LocalDateTime endsOn;

    @Column(name = "provider_id", nullable = false)
    private Long providerId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

}
