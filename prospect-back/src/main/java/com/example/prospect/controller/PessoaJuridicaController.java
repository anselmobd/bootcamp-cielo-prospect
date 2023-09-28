package com.example.prospect.controller;

import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.entity.input.EntradaPessoaJuridica;
import com.example.prospect.service.PessoaJuridicaService;
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

    @Operation(
            summary = "Consulta pessoa jurídica por id",
            description = "Consulta dados de uma pessoa jurídica pelo id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta executada com sucesso",
                    content = {@Content(
                            schema = @Schema(implementation = PessoaJuridica.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa jurídica não encontrada",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-27T22:40:04.632+00:00\"," +
                                            "\"status\": 404," +
                                            "\"error\": \"Not Found\"," +
                                            "\"message\": \"Pessoa não encontrada com id: 42\"," +
                                            "\"path\": \"/api/v1/pessoa_juridica/42\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @GetMapping("/{id}")
    public PessoaJuridica getPessoaJuridica(
            @PathVariable
            @Parameter(name = "id", description = "Id da pessoa jurídica", example = "1")
            long id
    ) {
        return this.pessoaJuridicaService.getPessoaJuridica(id);
    }

    @Operation(
            summary = "Cria pessoa jurídica",
            description = "Insere dados de uma pessoa jurídica.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Inserção executada com sucesso",
                    content = {@Content(
                            schema = @Schema(implementation = PessoaJuridica.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflito de informações no banco de dados",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-27T23:11:36.714+00:00\"," +
                                            "\"status\": 409," +
                                            "\"error\": \"Conflict\"," +
                                            "\"message\": \"Já existe pessoa jurídica com CNPJ: 12345678000142\"," +
                                            "\"path\": \"/api/v1/pessoa_juridica\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "406",
                    description = "Valores não válidos",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"status\": 406," +
                                            "\"message\": \"Erro de validação do objeto\"," +
                                            "\"errors\": [" +
                                            "\"mcc: MCC deve ter no máximo 4 caracteres\"," +
                                            "\"razao_social: Razão social é obrigatória\"" +
                                            "]" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @PostMapping
    public PessoaJuridica addPessoaJuridica(@Valid @RequestBody EntradaPessoaJuridica pessoaJuridica) {
        return this.pessoaJuridicaService.addPessoaJuridica(pessoaJuridica);
    }

    @Operation(
            summary = "Exclusão de pessoa jurídica por id",
            description = "Exclusão dos dados de uma pessoa jurídica pelo id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Exclusão executada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa jurídica não encontrada",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-27T23:24:19.540+00:00\"," +
                                            "\"status\": 404," +
                                            "\"error\": \"Not Found\"," +
                                            "\"message\": \"Pessoa não encontrada com id: 1\"," +
                                            "\"path\": \"/api/v1/pessoa_juridica/1\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @DeleteMapping("/{id}")
    public void deletePessoaJuridica(
            @PathVariable
            @Parameter(name = "id", description = "Id da pessoa jurídica", example = "1")
            long id
    ) {
        this.pessoaJuridicaService.deletePessoaJuridica(id);
    }

    @Operation(
            summary = "Atualiza pessoa jurídica por id",
            description = "Atualiza dados de uma pessoa jurídica pelo id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Atualização executada com sucesso",
                    content = {@Content(
                            schema = @Schema(implementation = PessoaJuridica.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pessoa jurídica não encontrada",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-27T23:24:49.540+00:00\"," +
                                            "\"status\": 404," +
                                            "\"error\": \"Not Found\"," +
                                            "\"message\": \"Pessoa não encontrada com id: 1\"," +
                                            "\"path\": \"/api/v1/pessoa_juridica/1\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "406",
                    description = "Valores não válidos",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"status\": 406," +
                                            "\"message\": \"Erro de validação do objeto\"," +
                                            "\"errors\": [" +
                                            "\"mcc: MCC é obrigatório\"" +
                                            "]" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflito de informações no banco de dados",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-27T23:41:36.714+00:00\"," +
                                            "\"status\": 409," +
                                            "\"error\": \"Conflict\"," +
                                            "\"message\": \"Já existe pessoa jurídica com CNPJ: 12345678000142\"," +
                                            "\"path\": \"/api/v1/pessoa_juridica\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @PutMapping("/{id}")
    public PessoaJuridica updatePessoaJuridica(
            @Valid @RequestBody EntradaPessoaJuridica pessoaJuridica,
            @PathVariable
            @Parameter(name = "id", description = "Id da pessoa jurídica", example = "1")
            long id) {
        return this.pessoaJuridicaService.updatePessoaJuridica(pessoaJuridica, id);
    }

}
