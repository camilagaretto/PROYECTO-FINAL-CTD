package com.equipo2.Appkademy.rest.dto.filter;


import com.equipo2.Appkademy.core.model.entity.TeachingProficiency;
import com.equipo2.Appkademy.core.model.enums.City;
import com.equipo2.Appkademy.core.model.enums.Country;
import com.equipo2.Appkademy.core.model.enums.Province;
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

    private List<Long> teacherIds;
    private TeachingProficiency teachingProficiency;
    private Country country;
    private Province province;
    private City city;

}