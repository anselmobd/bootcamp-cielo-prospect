package com.example.prospect.entity.input;

import com.example.prospect.entity.PessoaJuridica;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

@Entity
@Schema(name = "Entrada de pessoa jurídica", description = "Dados para entrada de pessoa jurídica")
public class EntradaPessoaJuridica extends PessoaJuridica {

    @Schema(example = "42", hidden = true)
    private long id;

}