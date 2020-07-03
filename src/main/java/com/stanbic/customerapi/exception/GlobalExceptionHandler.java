package com.stanbic.customerapi.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

// Valiation handler for input fields
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<?> customValidationErrorHandler(MethodArgumentNotValidException exception){
       ErrorDetails errorDetails = 
       new ErrorDetails(new Date(), "Validation Failed", exception.getBindingResult().getFieldError().getDefaultMessage());
       return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
   }
}