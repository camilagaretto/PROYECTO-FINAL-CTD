package com.equipo2.Appkademy.rest.error.handler;

import com.equipo2.Appkademy.rest.error.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleNotFound(NotFoundException exception){
        return new ResponseEntity<>(new RestErrorWithValidation("404", "NOT_FOUND", exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException exception){
        return new ResponseEntity<>(new RestErrorWithValidation("400", "BAD_REQUEST",
                exception.getMessage(), new Validation(exception.getInvalidAttribute(), exception.getInvalidValue())),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(ex.getMessage());

        return new ResponseEntity<>(new RestError("400", "BAD_REQUEST", errorMessage),
                                    HttpStatus.BAD_REQUEST);
    }
}
