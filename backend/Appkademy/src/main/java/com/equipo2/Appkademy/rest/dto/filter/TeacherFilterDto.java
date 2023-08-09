package com.equipo2.Appkademy.rest.dto.filter;


import com.equipo2.Appkademy.core.model.enums.City;
import com.equipo2.Appkademy.core.model.enums.Country;
import com.equipo2.Appkademy.core.model.enums.Province;
import com.equipo2.Appkademy.rest.dto.request.TeachingProficiencyDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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


    @Parameter(in = ParameterIn.QUERY
            , description = "Teacher ids"
            , name = "teacherIds"
            , required = false
            , array = @ArraySchema(schema = @Schema(allOf = {Integer.class}))
    )
    private List<Long> teacherIds;

    @Parameter(description = "Filter by teaching subject and mastery level")
    private TeachingProficiencyDto teachingProficiency;

    @Parameter(description = "Filter by country")
    private Country country;

    @Parameter(description = "Filter by province")
    private Province province;

    @Parameter(description = "Filter by city")
    private City city;

}