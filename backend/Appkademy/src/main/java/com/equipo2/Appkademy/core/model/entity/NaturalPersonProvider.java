package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class NaturalPersonProvider extends NaturalPerson {

    @Column(name = "provider_category_id", nullable = false)
    private Long providerCategoryId;

    @Column(name = "total_likes", nullable = false, columnDefinition = "BIGINT default false")
    private Long totalLikes;

    @Column(name = "profile_picture_url", nullable = true, length = 350)
    private String profilePictureUrl;

    @Column(name = "identity_verified", nullable = false, columnDefinition = "boolean default false")
    private boolean identityVerified;


}
