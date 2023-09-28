package com.example.prospect.controller;

import com.example.prospect.service.FilaPessoaVersaoService;
import com.example.prospect.util.PessoaVersao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Operation(
            summary = "Retira pessoa da fila",
            description = "Le e retira da fila a identificação da pessoa da vez.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pessoa retirada com sucesso",
                    content = {@Content(
                            schema = @Schema(implementation = PessoaVersao.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "A fila está vazia",
                    content = { @Content(
                            schema = @Schema(),
                            examples = {@ExampleObject(
                                    value = "{" +
                                            "\"timestamp\": \"2023-09-28T22:37:56.551+00:00\"," +
                                            "\"status\": 404," +
                                            "\"error\": \"Not Found\"," +
                                            "\"message\": \"Fila está vazia\"," +
                                            "\"path\": \"/api/v1/fila_pessoa\"" +
                                            "}"
                            )},
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            )
    })
    @GetMapping
    public PessoaVersao retiraPessoaVersao() {
        return filaPessoaVersaoService.retiraPessoaVersao();
    }
}
