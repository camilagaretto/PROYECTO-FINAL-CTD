package com.equipo2.Appkademy.core.security.model.repository;

import com.equipo2.Appkademy.core.security.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
