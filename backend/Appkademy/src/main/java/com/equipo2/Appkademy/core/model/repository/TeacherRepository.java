package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByUserId(Long userId);

    //@Query("SELECT * FROM Teacher AS t INNER JOIN teacher_characteristics AS c ON t.id = c.teacher_id WHERE c.characteristics_id = ?1")
    @Query(value = "SELECT t.* FROM teacher t JOIN teacher_characteristics tc ON t.id = tc.teacher_id WHERE tc.characteristics_id = ?1", nativeQuery = true)
    List<Teacher> findAllWithCharacteristicId(Long id);
}
