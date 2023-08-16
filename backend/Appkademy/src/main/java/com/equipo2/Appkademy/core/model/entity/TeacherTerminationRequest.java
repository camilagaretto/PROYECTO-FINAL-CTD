package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_termination_request")
public class TeacherTerminationRequest extends BaseSqlEntity<Long> {

    @Column(name = "request_created_on")
    private LocalDateTime requestCreatedOn;

    @ManyToOne
    @JoinColumn(name = "teacher_id", unique = true)
    private Teacher teacher;

}
