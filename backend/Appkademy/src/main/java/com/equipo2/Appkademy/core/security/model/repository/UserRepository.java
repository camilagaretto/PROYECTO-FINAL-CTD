package com.equipo2.Appkademy.core.security.model.repository;

import com.equipo2.Appkademy.core.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
