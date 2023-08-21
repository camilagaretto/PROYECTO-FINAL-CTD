package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "characteristic")
public class Characteristic extends BaseSqlEntity<Long> {

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "name", nullable = false)
    private String name;

}
