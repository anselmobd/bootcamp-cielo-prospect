package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.entity.input.EntradaPessoaFisica;
import com.example.prospect.entity.input.EntradaPessoaJuridica;
import com.example.prospect.exception.PessoaConflictException;
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

    public PessoaJuridica addPessoaJuridica(EntradaPessoaJuridica pessoaJuridica)
            throws PessoaConflictException {
        String cnpj = pessoaJuridica.getCnpj();
        boolean exists = this.pessoaJuridicaRepository.existsByCnpj(cnpj);
        if (exists) {
            throw new PessoaConflictException("Já existe pessoa jurídica com CNPJ: " + cnpj);
        }
        return this.pessoaJuridicaRepository.save(pessoaJuridica);
    }
}
