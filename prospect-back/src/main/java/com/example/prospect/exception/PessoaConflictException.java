package com.example.prospect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)  // 409
public class PessoaConflictException extends RuntimeException {
    public PessoaConflictException(String message) {
        super(message);
    }
    // ...
}
