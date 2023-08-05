package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Provider extends BaseSqlEntity<Long> {

    @Column(name = "provider_category_id", nullable = false)
    private Long providerCategoryId;

    @Column(name = "total_likes", nullable = false, columnDefinition = "BIGINT default false")
    private Long totalLikes;

    @Column(name = "profile_picture_url", nullable = true, length = 350)
    private String profilePictureUrl;

    @Column(name = "identity_verified", nullable = false, columnDefinition = "boolean default false")
    private boolean identityVerified;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled;

    @Column(name = "created_on", nullable = true)
    private LocalDateTime createdOn;

    @Column(name = "last_modified_on", nullable = true)
    private LocalDateTime lastModifiedOn;


}
