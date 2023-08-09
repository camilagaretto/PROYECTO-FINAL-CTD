package com.equipo2.Appkademy.rest.dto.filter;

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

    @Schema(title = "Page number", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageNumber;

    @Schema(title = "Page size", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;

}
