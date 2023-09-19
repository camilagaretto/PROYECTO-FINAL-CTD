package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.TeachingProficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import com.equipo2.Appkademy.core.model.entity.TeachingSubject;

public interface TeachingProficiencyRepository extends JpaRepository<TeachingProficiency, Long> {
    void deleteByTeachingSubject(TeachingSubject subjectToDelete);
    }

