package com.example.prospect.service;

import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.entity.input.EntradaPessoaJuridica;
import com.example.prospect.exception.PessoaConflictException;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PessoaJuridica getPessoaJuridica(long id) throws PessoaNotFoundException {
        Optional<PessoaJuridica> optionalPessoaJuridica = this.pessoaJuridicaRepository.findById(id);
        if (optionalPessoaJuridica.isEmpty()) {
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        return optionalPessoaJuridica.get();
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

    public void deletePessoaJuridica(long id) throws PessoaNotFoundException {
        boolean exists = this.pessoaJuridicaRepository.existsById(id);
        if (!exists) {
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        this.pessoaJuridicaRepository.deleteById(id);
    }

    public PessoaJuridica updatePessoaJuridica(
            EntradaPessoaJuridica atualizaPessoaJuridica, long id
    ) throws PessoaNotFoundException, PessoaConflictException {
        Optional<PessoaJuridica> optionalPessoaJuridica = this.pessoaJuridicaRepository.findById(id);
        if (optionalPessoaJuridica.isEmpty())
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);

        String cnpj = atualizaPessoaJuridica.getCnpj();
        if (
                !cnpj.equals(optionalPessoaJuridica.get().getCnpj())
                        && this.pessoaJuridicaRepository.existsByCnpj(cnpj)
        ) {
            throw new PessoaConflictException("Já existe pessoa jurídica com CNPJ: " + cnpj);
        }

        atualizaPessoaJuridica.setId(id);
        return this.pessoaJuridicaRepository.save(atualizaPessoaJuridica);
    }

}
