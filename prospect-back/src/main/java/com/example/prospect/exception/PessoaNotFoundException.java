package com.example.prospect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Pessoa não encontrada")  // 404
public class PessoaNotFoundException extends RuntimeException {
    // ...
}
