package com.koodu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Abiola.Adebanjo
 */
@ControllerAdvice
public class TimeExceptionHandler {

    @ExceptionHandler(TimeException.class)
    public ResponseEntity<ErrorResponse> constraintViolation(TimeException te) {
        return new ResponseEntity<>(te.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> constraintViolation(Exception te) {
        return new ResponseEntity<>(new ErrorResponse("01", "Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
