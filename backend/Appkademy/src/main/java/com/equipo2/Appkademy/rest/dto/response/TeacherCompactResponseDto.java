package com.equipo2.Appkademy.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCompactResponseDto {

    private Long id;
    private Long providerCategoryId;
    private String firstName;
    private String lastName;
    private Long totalLikes;
    private String shortDescription;
    private AddressResponseDto address;
    private String profilePictureUrl;
    private boolean identityVerified;
    List<TeachingProficiencyResponseDto> proficiencies;

}
