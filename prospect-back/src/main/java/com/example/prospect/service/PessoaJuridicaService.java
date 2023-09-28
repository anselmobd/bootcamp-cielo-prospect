package com.example.prospect.service;

import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    public List<PessoaJuridica> getPessoasJuridicas() {
        return this.pessoaJuridicaRepository.findAll();
    }
}
