package com.example.prospect.controller;

import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Pessoa Jurídica", description = "APIs de apoio à prospecção de clientes pessoa jurídica")
@RestController
@RequestMapping("/api/v1/pessoa_juridica")
public class PessoaJuridicaController {

    private final PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @Operation(
            summary = "Lista pessoas jurídicas",
            description = "Lista todas as pessoas jurídicas cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta executada com sucesso",
                    content = {@Content(
                            array = @ArraySchema(schema = @Schema(implementation = PessoaJuridica.class)),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @GetMapping
    public List<PessoaJuridica> getPessoasJuridicas() {
        return this.pessoaJuridicaService.getPessoasJuridicas();
    }
}
