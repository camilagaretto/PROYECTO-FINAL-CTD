package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student extends NaturalPersonCustomer {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private List<ScheduledAppointment> scheduledAppointments;

}
