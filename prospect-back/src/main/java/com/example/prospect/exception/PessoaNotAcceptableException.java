package com.example.prospect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)  // 406
public class PessoaNotAcceptableException extends RuntimeException {
    public PessoaNotAcceptableException(String message) {
        super(message);
    }
    // ...
}
