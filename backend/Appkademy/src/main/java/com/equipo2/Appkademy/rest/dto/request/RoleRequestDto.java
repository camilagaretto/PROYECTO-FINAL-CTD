package com.equipo2.Appkademy.rest.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4848185802287128978L;

    private List<Long> roleIds;

}
