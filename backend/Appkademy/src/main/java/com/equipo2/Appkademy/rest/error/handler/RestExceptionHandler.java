package com.equipo2.Appkademy.rest.error.handler;

import com.equipo2.Appkademy.rest.error.NotFoundException;
import com.equipo2.Appkademy.rest.error.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException exception){
        return new ResponseEntity<>(new RestError("404", "NOT_FOUND", exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}
