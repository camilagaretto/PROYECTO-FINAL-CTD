package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.TeachingSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeachingSubjectRepository extends JpaRepository<TeachingSubject, Long> {


    Optional<TeachingSubject> findByName(String name);

    @Query("SELECT DISTINCT name FROM TeachingSubject")
    List<String> findDistinctByName();

    void deleteById(Long id);

}
