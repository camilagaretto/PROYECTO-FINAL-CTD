package com.equipo2.Appkademy.core.security.model.repository;

import com.equipo2.Appkademy.core.model.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacteristicRespository extends JpaRepository<Characteristic, Long> {

    Optional<Characteristic> findByName(String name);
}
