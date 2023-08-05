package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weekly_working_schedule")
public class WeeklyWorkingSchedule extends BaseSqlEntity<Long> {

    //private List<DayOfWeek> workingDays;
    //@ElementCollection
    //private Map<DayOfWeek, Boolean> workingDays;

    @Column(name = "check_in", nullable = false)
    private LocalTime checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalTime checkOut;

    @Column(columnDefinition = "boolean default false")
    private boolean sunday;
    @Column(columnDefinition = "boolean default false")
    private boolean monday;
    @Column(columnDefinition = "boolean default false")
    private boolean tuesday;
    @Column(columnDefinition = "boolean default false")
    private boolean wednesday;
    @Column(columnDefinition = "boolean default false")
    private boolean thursday;
    @Column(columnDefinition = "boolean default false")
    private boolean friday;
    @Column(columnDefinition = "boolean default false")
    private boolean saturday;

}
