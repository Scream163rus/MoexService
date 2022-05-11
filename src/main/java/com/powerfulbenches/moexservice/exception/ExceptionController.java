package com.powerfulbenches.moexservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BondsNotFoundException.class})
    public ResponseEntity<ErrorDto> bondNotFoundExceptionHandler(Exception exception){
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BondsParseException.class})
    public ResponseEntity<ErrorDto> bondsParsingExceptionHandler(Exception exception){
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({LimitRequestException.class})
    public ResponseEntity<ErrorDto> limitRequestExceptionHandler(Exception exception){
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.TOO_MANY_REQUESTS);
    }
}
