package com.example.prospect.service;

import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.entity.input.EntradaPessoaJuridica;
import com.example.prospect.exception.PessoaConflictException;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaJuridicaRepository;
import com.example.prospect.util.FilaPessoaVersao;
import com.example.prospect.util.PessoaVersao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService {

    private final FilaPessoaVersao filaPessoaVersao;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    public PessoaJuridicaService(FilaPessoaVersao filaPessoaVersao, PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.filaPessoaVersao = filaPessoaVersao;
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
        PessoaJuridica savedPessoaJuridica = this.pessoaJuridicaRepository.save(pessoaJuridica);
        PessoaVersao pessoaVersao = new PessoaVersao(
                savedPessoaJuridica.getCnpj(),
                savedPessoaJuridica.getVersao()
        );
        filaPessoaVersao.inFila(pessoaVersao);
        return savedPessoaJuridica;
    }

    public PessoaJuridica deletePessoaJuridica(long id) throws PessoaNotFoundException {
        Optional<PessoaJuridica> optionalPessoaJuridica = this.pessoaJuridicaRepository.findById(id);
        if (optionalPessoaJuridica.isEmpty())
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        PessoaJuridica pessoaJuridica = optionalPessoaJuridica.get();
        this.pessoaJuridicaRepository.delete(pessoaJuridica);
        return pessoaJuridica;
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
        PessoaJuridica savedPessoaJuridica = this.pessoaJuridicaRepository.save(atualizaPessoaJuridica);
        PessoaVersao pessoaVersao = new PessoaVersao(
                savedPessoaJuridica.getCnpj(),
                savedPessoaJuridica.getVersao()
        );
        filaPessoaVersao.inFila(pessoaVersao);
        return savedPessoaJuridica;
    }

}
