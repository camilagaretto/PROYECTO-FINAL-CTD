package com.equipo2.Appkademy.rest.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableFilter {

    private Integer pageNumber;
    private Integer pageSize;

}
