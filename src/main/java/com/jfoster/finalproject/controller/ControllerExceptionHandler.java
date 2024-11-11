package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.service.InsufficientAccountBalanceException;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Spring MVC class to handle exceptions thrown from the service layer.
 */
@RestControllerAdvice
public class ControllerExceptionHandler  {

    /**
     * Handler for MethodArgumentNotValidException, thrown when an input to the controller fails Spring Validator requirements.
     * Check the DTO class definitions for more details on validation constraints.
     * @param ex Exception to be handled.
     * @param request Request that caused the exception.
     * @return Sends a custom ResponseEntity detailing that the response inherently failed controller-level validation constraints.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "One or more controller input validation requirements failed. Consult documentation to determine the cause.");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for IllegalArgumentException. Typically thrown at the service layer when a parameter passes Spring Validator requirements
     * but does not meet internal business logic requirements.
     * @param ex Exception to be handled.
     * @param request Request that caused the exception.
     * @return Sends a custom ResponseEntity detailing that the response failed at the service-layer.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "One or more service validation requirements failed. Consult documentation to determine the cause.");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for InsufficientAccountBalanceException.
     * Thrown when a credit is attempted (either directly or via a transaction) that would cause the account involved to exceed its maximum overdraft.
     * @param ex Exception to be handled.
     * @param request Request that caused the exception.
     * @return Sends a custom ResponseEntity informing the user that the proposed transaction is illegal.
     */
    @ExceptionHandler(InsufficientAccountBalanceException.class)
    public ResponseEntity<Object> handleInsufficientAccountBalanceException(InsufficientAccountBalanceException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "The account provided has insufficient funds to support a debit transaction.");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for NoResultException.
     * Thrown when a specific ID is requested as part of CRUD work that is not present in the underlying DB.
     * @param ex Exception to be handled.
     * @param request Request that caused the exception.
     * @return Sends a custom ResponseEntity informing the user that no records with the ID provided exist.
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Object> handleNoResultException(NoResultException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "A record with the ID provided does not exist within the database.");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
