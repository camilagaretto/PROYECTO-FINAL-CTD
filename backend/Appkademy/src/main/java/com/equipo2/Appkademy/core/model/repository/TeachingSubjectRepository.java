package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.TeachingSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachingSubjectRepository extends JpaRepository<TeachingSubject, Long> {


    Optional<TeachingSubject> findBySubject(String subject);
}
