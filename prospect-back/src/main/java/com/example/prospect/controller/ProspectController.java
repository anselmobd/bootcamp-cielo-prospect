package com.example.prospect.controller;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.service.ProspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta executada com sucesso",
                    content = {@Content(
                            array = @ArraySchema(schema = @Schema(implementation = PessoaFisica.class)),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @GetMapping("/pessoa_fisica")
    public List<PessoaFisica> getPessoasFisicas() {
        return this.prospectService.getPessoasFisicas();
    }

    @Operation(
            summary = "Consulta pessoa física por id",
            description = "Consulta dados de uma pessoa física pelo id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta executada com sucesso",
                    content = {@Content(
                            schema = @Schema(implementation = PessoaFisica.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa física não encontrada",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-27T20:40:04.632+00:00\"," +
                                            "\"status\": 404," +
                                            "\"error\": \"Not Found\"," +
                                            "\"message\": \"Pessoa não encontrada com id: 42\"," +
                                            "\"path\": \"/api/v1/pessoa_fisica/42\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @GetMapping("/pessoa_fisica/{id}")
    public PessoaFisica getPessoaFisica(
            @PathVariable
            @Parameter(name = "id", description = "Id da pessoa física", example = "1")
            long id
    ) {
        return this.prospectService.getPessoaFisica(id);
    }

    @Operation(
            summary = "Cria pessoa física",
            description = "Insere os dados de uma pessoa física.")
    @PostMapping("/pessoa_fisica")
    public PessoaFisica addPessoaFisica(@Valid @RequestBody PessoaFisica pessoaFisica) {
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
    public PessoaFisica updatePessoaFisica(@Valid @RequestBody PessoaFisica pessoaFisica, @PathVariable long id) {
        return this.prospectService.updatePessoaFisica(pessoaFisica, id);
    }
}
