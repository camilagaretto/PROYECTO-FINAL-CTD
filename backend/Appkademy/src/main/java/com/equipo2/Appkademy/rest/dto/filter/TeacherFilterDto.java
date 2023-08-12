package com.equipo2.Appkademy.rest.dto.filter;


import com.equipo2.Appkademy.core.model.enums.City;
import com.equipo2.Appkademy.core.model.enums.Country;
import com.equipo2.Appkademy.core.model.enums.Province;
import com.equipo2.Appkademy.rest.dto.request.TeachingProficiencyDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeacherFilterDto extends PageableFilter implements Serializable {

    @Serial
    private static final long serialVersionUID = -6074880344535199001L;


    @Schema(title = "Teacher ids", example = "[1, 15]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Long> teacherIds;

    @Schema(title = "Teaching proficiency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private TeachingProficiencyDto teachingProficiency;

    @Schema(title = "Country", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Country country;

    @Schema(title = "province", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Province province;

    @Schema(title = "City", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private City city;

    @Schema(title = "Results should be randomized", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private boolean randomOrder;

}