package com.example.prospect.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 11, message = "CPF deve ter 11 dígitos")
    @Pattern(regexp = "^\\d+$")
    @Column(unique = true, length = 11)
    private String cpf;

    @NotBlank(message = "MCC é obrigatório")
    @Size(max = 4, message = "MCC deve ter no máximo 4 caracteres")
    @Column(length = 4)
    private String mcc;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    @Column(length = 50)
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail não é válido", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Column(length = 50)
    private String email;

    public PessoaFisica() {}

    public PessoaFisica(String cpf, String mcc, String nome, String email) {
        this.cpf = cpf;
        this.mcc = mcc;
        this.nome = nome;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = StringUtils.leftPad(cpf, 11, "0");
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
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
        return "PessoaFisica{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", mcc='" + mcc + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}