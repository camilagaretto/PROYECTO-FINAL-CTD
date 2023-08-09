package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class NaturalPersonCustomer extends NaturalPerson {

    @ElementCollection
    @CollectionTable(name = "liked_provider_id", joinColumns = @JoinColumn(name = "natural_person_customer_id"))
    @Column(name = "provider_id")
    private List<Long> likedProviderIds;

}
