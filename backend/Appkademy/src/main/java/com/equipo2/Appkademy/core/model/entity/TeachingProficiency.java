package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import com.equipo2.Appkademy.core.model.enums.TeachingSubject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teaching_proficiency")
public class TeachingProficiency extends BaseSqlEntity<Long>{

    @Enumerated(EnumType.STRING)
    @Column(name = "mastery_level", nullable = false)
    private TeachingMasteryLevel masteryLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject", nullable = false)
    private TeachingSubject subject;

}
