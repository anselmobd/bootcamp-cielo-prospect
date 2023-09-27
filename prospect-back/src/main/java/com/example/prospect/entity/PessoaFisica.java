package com.example.prospect.entity;

import com.example.prospect.exception.PessoaNotAcceptableException;
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
    @Column(unique = true, length = 11)
    private String cpf;

    @NotBlank(message = "MCC é obrigatório")
    @Size(min = 1, max = 4, message = "MCC deve ter no máximo 4 dígitos")
    @Column(length = 4)
    private String mcc;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 1, max = 50, message = "Nome deve ter no máximo 50 caracteres")
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

    public void setCpf(String cpf) throws PessoaNotAcceptableException {
        try {
            this.cpf = String.format("%011d", Integer.parseInt(cpf));
        } catch (Exception e) {
            throw new PessoaNotAcceptableException("CPF inválido");
        }
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        try {
            this.mcc = mcc;
        } catch (Exception e) {
            throw new PessoaNotAcceptableException("MCC inválido");
        }
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