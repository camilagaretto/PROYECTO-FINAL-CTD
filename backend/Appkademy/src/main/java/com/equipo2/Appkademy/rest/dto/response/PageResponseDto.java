package com.equipo2.Appkademy.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 4391048210623797116L;

    private Integer totalPagesFound;
    private Long totalItemsFound;
    private Integer pageSizeSelected;
    private Integer pageNumberSelected;
    private List<T> searchResults;

}
