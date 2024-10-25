package org.example.tz4krainet.Controllers;

import org.example.tz4krainet.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Глобальный обработчик исключений
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Пользователь не найден
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    //  Проект не найден
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<String> handleProjectNotFound(ProjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    // Проект найден
    @ExceptionHandler(ProjectWasFoundException.class)
    public ResponseEntity<String> handleProjectFound(ProjectWasFoundException ex) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ex.getMessage());
    }
    // Пользователь найден
    @ExceptionHandler(UserWasFoundException.class)
    public ResponseEntity<String> handleUsersFound(UserWasFoundException ex) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ex.getMessage());
    }
    // Пароль не правильный
    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<String> handlePasswordIncorrect(PasswordIncorrectException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Не все для сохранение
    @ExceptionHandler(NotAllComponentToSaveException.class)
    public ResponseEntity<String> handleInputIncorrect(NotAllComponentToSaveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}