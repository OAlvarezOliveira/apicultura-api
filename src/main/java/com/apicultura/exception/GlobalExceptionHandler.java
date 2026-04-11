package com.apicultura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ColmenaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ColmenaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
            "error", ex.getMessage(),
            "codigo", 404,
            "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler(ColmenaYaExisteException.class)
    public ResponseEntity<Map<String, Object>> handleConflict(ColmenaYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
            "error", ex.getMessage(),
            "codigo", 409,
            "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "error", ex.getMessage(),
            "codigo", 400,
            "timestamp", LocalDateTime.now().toString()
        ));
    }
}