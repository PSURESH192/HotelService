package com.springboot.project.HotelService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HotelExceptionController {
    @ExceptionHandler(value = IDNotFoundException.class)
    public ResponseEntity<Object> exception(IDNotFoundException exception) {
        return new ResponseEntity<>("Invalid ID or ID Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
