package com.example.prospect.repository;

import com.example.prospect.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaJuridicaRepository
        extends JpaRepository<PessoaJuridica, Long> {

    Optional<PessoaJuridica> findPessoaJuridicaByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}
