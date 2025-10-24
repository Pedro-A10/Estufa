package com.PedroA10.Estufa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.CONFLICT.value(), // 409
      ex.getMessage(), // mensagem da exceção
      System.currentTimeMillis() // timestamp atual
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }
  @ExceptionHandler(EmailNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEmailNotFound(EmailNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      ex.getMessage(),
      System.currentTimeMillis()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      ex.getMessage(),
      System.currentTimeMillis()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

}
