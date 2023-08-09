package com.equipo2.Appkademy.rest.dto.filter;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableFilter {

    @Parameter(in = ParameterIn.QUERY
                , description = "Page number"
                , name = "PageNumber"
                , required = false
                , schema = @Schema(type = "integer", defaultValue = "1")
    )
    private Integer pageNumber;

    @Parameter(in = ParameterIn.QUERY
            , description = "Page Size"
            , name = "PageSize"
            , required = false
            , schema = @Schema(type = "integer", defaultValue = "10")
    )
    @Schema(title = "Page size", example = "10")
    private Integer pageSize;

}
