package com.example.prospect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)  // 406
public class PessoaBadRequestException extends RuntimeException {
    public PessoaBadRequestException(String message) {
        super(message);
    }
    // ...
}
