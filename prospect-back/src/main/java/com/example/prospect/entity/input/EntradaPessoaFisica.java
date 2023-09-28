package com.example.prospect.entity.input;

import com.example.prospect.entity.PessoaFisica;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@Schema(name = "Entrada de pessoa física", description = "Dados para entrada de pessoa física")
public class EntradaPessoaFisica extends PessoaFisica {

    @Schema(example = "42", hidden = true)
    private long id;

    @Schema(example = "2023-09-28T23:14:51.204+00:00", hidden = true)
    private Date versao;

}