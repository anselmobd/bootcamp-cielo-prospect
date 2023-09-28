package com.example.prospect.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PessoaFisicaTests {

    @Autowired
    private Validator validator;

    @Test
    public void PessoaFisica_Cpf_Valid() {
        PessoaFisica pessoaFisica = this.basePessoaFisica();
        Set<ConstraintViolation<PessoaFisica>> violations = new HashSet<>();

        // Valores válidos
        pessoaFisica.setCpf("");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setCpf("123");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setCpf("12345678901");
        violations.addAll(validator.validate(pessoaFisica));

        // Valores inválidos
        pessoaFisica.setCpf(null);
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setCpf("text");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setCpf("123456789012");
        violations.addAll(validator.validate(pessoaFisica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaFisica_Mcc_Valid() {
        PessoaFisica pessoaFisica = this.basePessoaFisica();
        Set<ConstraintViolation<PessoaFisica>> violations = new HashSet<>();

        // Valores válidos
        pessoaFisica.setMcc("1");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setMcc("1234");
        violations.addAll(validator.validate(pessoaFisica));

        // Valores inválidos
        pessoaFisica.setMcc(null);
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setMcc("");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setMcc("12345");
        violations.addAll(validator.validate(pessoaFisica));

        System.out.println(violations);

        assertEquals(3, violations.stream().count());
    }

    private PessoaFisica basePessoaFisica() {
        return PessoaFisica.builder()
                .cpf("00987654321")
                .mcc("a1b2")
                .nome("Fulano de Tal")
                .email("fulano.tal@dominio.ccc")
                .build();
    }
}