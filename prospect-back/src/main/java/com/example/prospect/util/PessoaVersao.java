package com.example.prospect.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Schema(name = "Pessoa Versão", description = "Cadastro (CPF/CNPJ) e versão das informações da pessoa")
public class PessoaVersao {

    @Schema(example = "12345678901234", description = "Cadastro nacional (CPF/CNPJ)")
    private String cadastro;

    @Schema(example = "2023-09-28T23:14:51.204+00:00", description = "Data e hora da versão das informações gravadas da pessoa")
    private Date versao;

}
