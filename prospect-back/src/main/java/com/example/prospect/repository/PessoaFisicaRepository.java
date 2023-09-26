package com.example.prospect.repository;

import com.example.prospect.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository
        extends JpaRepository<PessoaFisica, Long> {
}
