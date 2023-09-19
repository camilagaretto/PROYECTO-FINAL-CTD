package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private TeachingSubject subject;

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((TeachingProficiency) obj).getId());
    }
}
