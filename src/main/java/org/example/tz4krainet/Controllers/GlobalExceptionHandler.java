package org.example.tz4krainet.Controllers;

import org.example.tz4krainet.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Глобальный обработчик исключений
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<String> handleProjectNotFound(ProjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectWasFoundException.class)
    public ResponseEntity<String> handleProjectFound(ProjectWasFoundException ex) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(UserWasFoundException.class)
    public ResponseEntity<String> handleUsersFound(UserWasFoundException ex) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<String> handlePasswordIncorrect(PasswordIncorrectException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(NotAllComponentToSaveException.class)
    public ResponseEntity<String> handleInputIncorrect(NotAllComponentToSaveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    // Можно добавить обработку других исключений
}