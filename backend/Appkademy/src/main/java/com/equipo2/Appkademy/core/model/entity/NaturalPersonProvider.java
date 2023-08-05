package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class NaturalPersonProvider extends Provider {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "short_description", nullable = true, length = 100)
    private String shortDescription;

    @Column(name = "full_description", nullable = true, columnDefinition = "TEXT")
    private String fullDescription;

    @Embedded
    private Address address = new Address();

}
