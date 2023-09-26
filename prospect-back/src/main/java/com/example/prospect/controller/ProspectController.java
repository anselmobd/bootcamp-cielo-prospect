package com.example.prospect.controller;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.service.ProspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Prospect", description = "APIs de apoio à prospecção de clientes")
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
            summary = "Consulta pessoa física por id",
            description = "Consulta dados de uma pessoa física pelo id.")
    @GetMapping("/pessoa_fisica/{id}")
    public PessoaFisica getPessoaFisica(@PathVariable long id) {
        return this.prospectService.getPessoaFisica(id);
    }

    @Operation(
            summary = "Cria pessoa física",
            description = "Insere os dados de uma pessoa física.")
    @PostMapping("/pessoa_fisica")
    public PessoaFisica addPessoaFisica(@RequestBody PessoaFisica pessoaFisica) {
        return this.prospectService.addPessoaFisica(pessoaFisica);
    }

    @Operation(
            summary = "Apaga pessoa física por id",
            description = "Apaga os dados de uma pessoa física pelo id.")
    @DeleteMapping("/pessoa_fisica/{id}")
    public void deletePessoaFisica(@PathVariable long id) {
        this.prospectService.deletePessoaFisica(id);
    }

    @Operation(
            summary = "Atualiza pessoa física por id",
            description = "Atualiza dados de uma pessoa física pelo id.")
    @PutMapping("/pessoa_fisica/{id}")
    public PessoaFisica updatePessoaFisica(@RequestBody PessoaFisica pessoaFisica, @PathVariable long id) {
        return this.prospectService.updatePessoaFisica(pessoaFisica, id);
    }
}
