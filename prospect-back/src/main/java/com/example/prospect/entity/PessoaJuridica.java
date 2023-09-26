package com.example.prospect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos")
    private String cnpj;

    @NotBlank(message = "Razão social é obrigatória")
    @Size(max = 50, message = "Razão social deve ter no máximo 50 caracteres")
    private String razao_social;

    @NotBlank(message = "MCC é obrigatório")
    @Size(max = 4, message = "MCC deve ter no máximo 4 dígitos")
    private String mcc;

    @NotBlank(message = "CPF do contato do estabelecimento é obrigatório")
    @Size(min = 11, max = 11, message = "CPF do contato do estabelecimento deve ter 11 dígitos")
    private String cpf;

    @NotBlank(message = "Nome do contato do estabelecimento é obrigatório")
    @Size(max = 50, message = "Nome do contato do estabelecimento deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "E-mail do contato do estabelecimento é obrigatório")
    @Email(message = "E-mail do contato do estabelecimento não é valido", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
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
        this.cnpj = cnpj;
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
        this.cpf = cpf;
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