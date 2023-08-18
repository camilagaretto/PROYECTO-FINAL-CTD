package com.equipo2.Appkademy.core.security.model.repository;

import com.equipo2.Appkademy.core.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
