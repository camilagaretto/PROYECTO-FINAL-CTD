package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
