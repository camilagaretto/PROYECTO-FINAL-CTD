package com.equipo2.Appkademy.rest.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2371478962267333895L;

    private String url;

}
