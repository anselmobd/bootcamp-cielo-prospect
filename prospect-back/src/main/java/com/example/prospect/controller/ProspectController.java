package com.example.prospect.controller;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.service.ProspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Prospect", description = "APIs de envio e verificação de Feedbacks")
@RestController
@RequestMapping("/api/v1")
public class ProspectController {

    private final ProspectService prospectService;

    @Autowired
    public ProspectController(ProspectService prospectService) {
        this.prospectService = prospectService;
    }

    @Operation(
            summary = "Lista pessoas físicas",
            description = "Lista todas as pessoas físicas cadastradas.")
    @GetMapping("/pessoa_fisica")
    public List<PessoaFisica> getPessoasFisicas() {
        return this.prospectService.getPessoasFisicas();
    }

    @Operation(
            summary = "Pega pessoa física por id",
            description = "Pega dados de uma pessoa física pelo id.")
    @GetMapping("/pessoa_fisica/{id}")
    public PessoaFisica getPessoaFisica(@PathVariable long id) {
        return this.prospectService.getPessoaFisica(id);
    }
}
