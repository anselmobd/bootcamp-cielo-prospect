package com.example.prospect.exception.handler;

import com.example.prospect.exception.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class CustomValidationExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(BindException ex) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        ArrayList<String> errors = new ArrayList<>();
        FieldError fieldError;

        for (ObjectError error : ex.getAllErrors()) {
            if (error instanceof FieldError) {
                fieldError = (FieldError) error;
                errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errors.add(error.getDefaultMessage());
            }
        }

        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        response.setMessage("Erro de validação do objeto");
        response.setErrors(errors);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }
}



