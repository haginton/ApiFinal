package com.ada.sql.sql.repository.postgresql.rol;

import com.ada.sql.sql.model.sql.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositoryJpa extends JpaRepository<Rol, Long> {
}
