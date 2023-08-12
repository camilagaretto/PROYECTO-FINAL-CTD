package com.equipo2.Appkademy.core.model.repository;

import com.equipo2.Appkademy.core.model.entity.ScheduledAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduledAppointmentRepository extends JpaRepository<ScheduledAppointment, Long> {

    Optional<List<ScheduledAppointment>> findByTeacherId(Long id);

}
