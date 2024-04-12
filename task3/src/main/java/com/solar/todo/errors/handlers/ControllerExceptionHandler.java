package com.solar.todo.errors.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Serves to intercept and handle errors in controllers,
 * the controller must implement this interface.
 */
public interface ControllerExceptionHandler {
    /**
     * Intercepts validation errors of incoming DTOs and displays their descriptions.
     * @param argumentNotValidException Validation error.
     * @return HashMap: the key is name of  parameter that has not passed
     * and the value is a message describing the validation error.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    default Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException argumentNotValidException
    ) {
        Map<String, String> errors = new HashMap<>();
        argumentNotValidException
            .getBindingResult()
            .getAllErrors()
            .forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMassage = error.getDefaultMessage();
                errors.put(fieldName, errorMassage);
            });
        return errors;
    }
}
