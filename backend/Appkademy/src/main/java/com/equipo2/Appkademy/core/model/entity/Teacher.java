package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teacher")
public class Teacher extends NaturalPersonProvider {

    @ElementCollection
    @MapKeyColumn(name = "currency")
    @CollectionTable(name = "teacher_hourly_rate", joinColumns = @JoinColumn(name = "teacher_id"))
    private Map<Currency, BigDecimal> hourlyRates;

    //TODO: persist modality enum as string? currently is persisted as ordinal
    @ElementCollection
    @MapKeyColumn(name = "modality")
    @CollectionTable(name = "teacher_modality", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "value")
    private Map<Modality, Boolean> modalities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = false)
    private List<TeachingProficiency> proficiencies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weekly_working_schedule_id", nullable = false)
    private WeeklyWorkingSchedule weeklyWorkingSchedule;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = false)
    private List<ScheduledAppointment> scheduledAppointments;

}
