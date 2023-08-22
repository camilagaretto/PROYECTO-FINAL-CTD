package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class NaturalPerson extends BaseSqlEntity<Long> {

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled;

    @Column(name = "identity_verified", nullable = false, columnDefinition = "boolean default false")
    private boolean identityVerified;

    @Embedded
    private Address address = new Address();

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "last_modified_on", nullable = false)
    private LocalDateTime lastModifiedOn;

}
