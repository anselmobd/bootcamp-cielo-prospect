package com.example.prospect.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Schema(name = "Pessoa física", description = "Dados de armazenamento de pessoa física")
@Entity
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "42")
    private long id;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @Pattern(regexp = "^\\d+$", message = "CPF deve ser totalmente numérico")
    @Column(unique = true, length = 11)
    @Schema(example = "00987654321")
    private String cpf;

    @NotBlank(message = "MCC é obrigatório")
    @Size(max = 4, message = "MCC deve ter no máximo 4 caracteres")
    @Column(length = 4)
    @Schema(example = "a1b2")
    private String mcc;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    @Column(length = 50)
    @Schema(example = "Fulano de Tal")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail não é válido", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Column(length = 50)
    @Schema(example = "fulano.tal@dominio.ccc")
    private String email;

    public PessoaFisica() {}

    public PessoaFisica(String cpf, String mcc, String nome, String email) {
        this.cpf = cpf;
        this.mcc = mcc;
        this.nome = nome;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = StringUtils.leftPad(cpf, 11, "0");
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", mcc='" + mcc + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}