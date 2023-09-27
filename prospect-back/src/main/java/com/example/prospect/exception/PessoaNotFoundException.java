package com.example.prospect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException(String message) {
        super(message);
    }
    // ...
}

@ResponseStatus(value= HttpStatus.CONFLICT)  // 409
public class PessoaConflictException extends RuntimeException {
    public PessoaConflictException(String message) {
        super(message);
    }
    // ...
}

@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 406
public class PessoaBadRequestException extends RuntimeException {
    public PessoaBadRequestException(String message) {
        super(message);
    }
    // ...
}
