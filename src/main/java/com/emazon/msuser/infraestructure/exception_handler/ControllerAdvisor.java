package com.emazon.msuser.infraestructure.exception_handler;

import com.emazon.msuser.domain.exception.AgeValidationException;
import com.emazon.msuser.domain.exception.MailAlreadyExistsException;
import com.emazon.msuser.domain.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AgeValidationException.class)
    public ResponseEntity<ExceptionResponse> handleAgeValidationException(AgeValidationException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(ConstantsException.AgeValidationException),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleMailAlreadyExistsException(MailAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(ConstantsException.MailAlreadyExistsException),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(ConstantsException.DocumentNumberAlreadyExistsException),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(ConstantsException.InvalidCredentialsException),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
