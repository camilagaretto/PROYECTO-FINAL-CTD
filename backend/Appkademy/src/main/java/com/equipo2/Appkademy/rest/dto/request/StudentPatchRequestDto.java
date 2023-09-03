package com.equipo2.Appkademy.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StudentPatchRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4605607746502129824L;

    @Schema(title = "Liked teacher id", example = "[1, 2]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long likedTeacherId;

}
