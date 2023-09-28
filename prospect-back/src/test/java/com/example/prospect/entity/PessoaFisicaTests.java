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

    private final PessoaFisica pessoaFisica;
    Set<ConstraintViolation<PessoaFisica>> violations;
    @Autowired
    private Validator validator;

    public PessoaFisicaTests() {
        pessoaFisica = PessoaFisica.builder()
                .cpf("00987654321")
                .mcc("a1b2")
                .nome("Fulano de Tal")
                .email("fulano.tal@dominio.ccc")
                .build();

    }

    @Test
    public void PessoaFisica_Cpf_Valid() {
        violations = new HashSet<>();

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
        violations = new HashSet<>();

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

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaFisica_Nome_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaFisica.setNome("A");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setNome("A".repeat(50));
        violations.addAll(validator.validate(pessoaFisica));

        // Valores inválidos
        pessoaFisica.setNome(null);
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setNome("");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setNome("A".repeat(51));
        violations.addAll(validator.validate(pessoaFisica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaFisica_Email_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaFisica.setEmail("a@b.cd");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setNome("abc.ABD-123_@abc.ABD-123_.abcAB");
        violations.addAll(validator.validate(pessoaFisica));

        // Valores inválidos
        pessoaFisica.setNome(null);
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setNome("");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("@b.cd");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("ab.cd");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("a@.cd");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("a@bcd");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("a@b.");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("1@1.1");
        violations.addAll(validator.validate(pessoaFisica));
        pessoaFisica.setEmail("1@1.123456");
        violations.addAll(validator.validate(pessoaFisica));

        assertEquals(9, violations.stream().count());
    }
}