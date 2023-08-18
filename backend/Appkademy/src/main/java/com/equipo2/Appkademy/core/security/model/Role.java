package com.equipo2.Appkademy.core.security.model;

import com.equipo2.Appkademy.core.model.entity.BaseSqlEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role extends BaseSqlEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",  // Name of the join table
            joinColumns = @JoinColumn(name = "role_id"),  // Column in this entity's table
            inverseJoinColumns = @JoinColumn(name = "permission_id")  // Column in the related entity's table
    )
    private List<Permission> permissions;

}
