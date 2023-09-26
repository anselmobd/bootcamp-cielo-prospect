package com.example.prospect.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @Column(unique=true)
    private String cpf;

    @NotBlank(message = "MCC é obrigatório")
    @Size(max = 4, message = "MCC deve ter no máximo 4 dígitos")
    private String mcc;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail não é valido", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
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
        this.cpf = cpf;
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