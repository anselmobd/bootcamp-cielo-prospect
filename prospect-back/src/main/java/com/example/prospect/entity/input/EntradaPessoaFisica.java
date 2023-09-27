package com.example.prospect.entity.input;

import com.example.prospect.entity.PessoaFisica;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Entrada de pessoa física", description = "Dados para entrada de pessoa física")
public class EntradaPessoaFisica extends PessoaFisica {

    @Schema(example = "42", hidden = true)
    private long id;

}