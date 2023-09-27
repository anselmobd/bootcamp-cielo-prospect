package com.example.prospect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 406
public class PessoaBadRequestException extends RuntimeException {
    public PessoaBadRequestException(String message) {
        super(message);
    }
    // ...
}
