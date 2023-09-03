package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.TeachingProficiency;
import com.equipo2.Appkademy.core.model.entity.TeachingSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingProficiencyRepository extends JpaRepository<TeachingProficiency, Long> {
    void deleteByTeachingSubject(TeachingSubject subjectToDelete);
}
