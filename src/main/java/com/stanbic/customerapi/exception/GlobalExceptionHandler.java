package com.stanbic.customerapi.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHander(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = 
        new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
 
 // handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception exception, WebRequest request){
        ErrorDetails errorDetails = 
        new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
     }
     
// Valiation handler for input fields
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<?> customValidationErrorHandler(MethodArgumentNotValidException exception){
       ErrorDetails errorDetails = 
       new ErrorDetails(new Date(), "Validation Failed", exception.getBindingResult().getFieldError().getDefaultMessage());
       return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
   }
}