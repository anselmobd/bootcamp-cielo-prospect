package com.example.prospect.controller;

import com.example.prospect.entity.superclass.PessoaSuperclass;
import com.example.prospect.service.FilaPessoaVersaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            description = "Lê e retira da fila a pessoa, física ou jurídica, da vez."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pessoa retirada com sucesso",
                    content = {@Content(
                            examples = {
                                    @ExampleObject(
                                        value = "[{" +
                                                "\"id\": 42," +
                                                "\"versao\": \"2023-09-28T23:14:51.204Z\"," +
                                                "\"cpf\": \"00987654321\"," +
                                                "\"mcc\": \"a1b2\"," +
                                                "\"nome\": \"Fulano de Tal\"," +
                                                "\"email\": \"fulano.tal@dominio.ccc\"" +
                                                "}]", name = "Pessoa Física"
                                    ),
                                    @ExampleObject(
                                        value = "[{" +
                                                "\"id\": 42," +
                                                "\"versao\": \"2023-09-28T23:14:51.204Z\"," +
                                                "\"cnpj\": \"12345678000142\"," +
                                                "\"razao_social\": \"Empresa Ltda.\"," +
                                                "\"mcc\": \"a1b2\"," +
                                                "\"cpf\": \"00987654321\"," +
                                                "\"nome\": \"Fulano de Tal\"," +
                                                "\"email\": \"fulano.tal@empresa.ccc\"" +
                                                "}]", name = "Pessoa Jurídica"
                                    )
                            },
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "A fila está vazia",
                    content = { @Content(
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
    public List<PessoaSuperclass> retiraPessoaVersao() {
        return filaPessoaVersaoService.retiraPessoaVersao();
    }
}
