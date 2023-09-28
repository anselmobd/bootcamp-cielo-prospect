package com.example.prospect.controller;

import com.example.prospect.service.FilaPessoaVersaoService;
import com.example.prospect.util.PessoaVersao;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Fila de pessoas", description = "APIs de apoio à prospecção de clientes")
@RestController
@RequestMapping("/api/v1/fila_pessoa")
public class FilaPessoaVersaoController {

    private final FilaPessoaVersaoService filaPessoaVersaoService;

    @Autowired
    public FilaPessoaVersaoController(FilaPessoaVersaoService filaPessoaVersaoService) {
        this.filaPessoaVersaoService = filaPessoaVersaoService;
    }

    @PostMapping
    public PessoaVersao outFila() {
        return filaPessoaVersaoService.outFila();
    }
}
