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
public class PessoaJuridicaTests {

    private final PessoaJuridica pessoaJuridica;
    Set<ConstraintViolation<PessoaJuridica>> violations;
    @Autowired
    private Validator validator;

    public PessoaJuridicaTests() {
        this.pessoaJuridica = PessoaJuridica.builder()
                .cnpj("12345678000142")
                .razao_social("Empresa Ltda.")
                .mcc("a1b2")
                .cpf("00987654321")
                .nome("Fulano de Tal")
                .email("fulano.tal@empresa.ccc")
                .build();
    }

    @Test
    public void PessoaJuridica_Cnpj_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaJuridica.setCnpj("");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCnpj("1");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCnpj("12345678901234");
        violations.addAll(validator.validate(pessoaJuridica));

        // Valores inválidos
        pessoaJuridica.setCnpj(null);
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCnpj("text");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCnpj("123456789012345");
        violations.addAll(validator.validate(pessoaJuridica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaJuridica_RazaoSocial_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaJuridica.setRazao_social("A");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setRazao_social("A".repeat(50));
        violations.addAll(validator.validate(pessoaJuridica));

        // Valores inválidos
        pessoaJuridica.setRazao_social(null);
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setRazao_social("");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setRazao_social("A".repeat(51));
        violations.addAll(validator.validate(pessoaJuridica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaJuridica_Mcc_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaJuridica.setMcc("1");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setMcc("1234");
        violations.addAll(validator.validate(pessoaJuridica));

        // Valores inválidos
        pessoaJuridica.setMcc(null);
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setMcc("");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setMcc("12345");
        violations.addAll(validator.validate(pessoaJuridica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaJuridica_Cpf_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaJuridica.setCpf("");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCpf("123");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCpf("12345678901");
        violations.addAll(validator.validate(pessoaJuridica));

        // Valores inválidos
        pessoaJuridica.setCpf(null);
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCpf("text");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setCpf("123456789012");
        violations.addAll(validator.validate(pessoaJuridica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaJuridica_Nome_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaJuridica.setNome("A");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setNome("A".repeat(50));
        violations.addAll(validator.validate(pessoaJuridica));

        // Valores inválidos
        pessoaJuridica.setNome(null);
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setNome("");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setNome("A".repeat(51));
        violations.addAll(validator.validate(pessoaJuridica));

        assertEquals(3, violations.stream().count());
    }

    @Test
    public void PessoaJuridica_Email_Valid() {
        violations = new HashSet<>();

        // Valores válidos
        pessoaJuridica.setEmail("a@b.cd");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setNome("abc.ABD-123_@abc.ABD-123_.abcAB");
        violations.addAll(validator.validate(pessoaJuridica));

        // Valores inválidos
        pessoaJuridica.setNome(null);
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setNome("");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("@b.cd");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("ab.cd");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("a@.cd");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("a@bcd");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("a@b.");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("1@1.1");
        violations.addAll(validator.validate(pessoaJuridica));
        pessoaJuridica.setEmail("1@1.123456");
        violations.addAll(validator.validate(pessoaJuridica));

        assertEquals(9, violations.stream().count());
    }
}