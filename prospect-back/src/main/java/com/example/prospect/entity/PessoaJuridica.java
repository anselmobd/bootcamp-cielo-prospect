package com.example.prospect.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

@Validated
@Schema(name = "Pessoa jurídica", description = "Dados de armazenamento de pessoa jurídica")
@Entity
public class PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "42")
    private long id;

    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos")
    @Pattern(regexp = "^\\d+$", message = "CNPJ deve ser totalmente numérico")
    @Column(unique = true, length = 14)
    @Schema(example = "12345678000142")
    private String cnpj;

    @NotBlank(message = "Razão social é obrigatória")
    @Size(max = 50, message = "Razão social deve ter no máximo 50 caracteres")
    @Column(length = 50)
    @Schema(example = "Empresa Ltda.")
    private String razao_social;

    @NotBlank(message = "MCC é obrigatório")
    @Size(max = 4, message = "MCC deve ter no máximo 4 caracteres")
    @Column(length = 4)
    @Schema(example = "a1b2")
    private String mcc;

    @NotBlank(message = "CPF do contato do estabelecimento é obrigatório")
    @Size(min = 11, max = 11, message = "CPF do contato do estabelecimento deve ter 11 dígitos")
    @Pattern(regexp = "^\\d+$", message = "CPF deve ser totalmente numérico")
    @Column(length = 11)
    @Schema(example = "00987654321")
    private String cpf;

    @NotBlank(message = "Nome do contato do estabelecimento é obrigatório")
    @Size(max = 50, message = "Nome do contato do estabelecimento deve ter no máximo 50 caracteres")
    @Column(length = 50)
    @Schema(example = "Fulano de Tal")
    private String nome;

    @NotBlank(message = "E-mail do contato do estabelecimento é obrigatório")
    @Email(message = "E-mail do contato do estabelecimento não é válido", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Column(length = 50)
    @Schema(example = "fulano.tal@empresa.ccc")
    private String email;

    public PessoaJuridica() {}

    public PessoaJuridica(String cnpj, String razao_social, String mcc, String cpf, String nome, String email) {
        this.cnpj = cnpj;
        this.razao_social = razao_social;
        this.mcc = mcc;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = StringUtils.leftPad(cnpj, 14, "0");
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = StringUtils.leftPad(cpf, 11, "0");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", razao_social='" + razao_social + '\'' +
                ", mcc='" + mcc + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}