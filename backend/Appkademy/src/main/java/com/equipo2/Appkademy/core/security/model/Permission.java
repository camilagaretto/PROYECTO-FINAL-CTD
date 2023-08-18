package com.equipo2.Appkademy.core.security.model;

import com.equipo2.Appkademy.core.model.entity.BaseSqlEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permission")
public class Permission extends BaseSqlEntity<Long> implements GrantedAuthority {

    String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
