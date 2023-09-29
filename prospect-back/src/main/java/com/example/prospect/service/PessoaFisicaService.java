package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.entity.input.EntradaPessoaFisica;
import com.example.prospect.exception.PessoaConflictException;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaFisicaRepository;
import com.example.prospect.util.FilaPessoaVersao;
import com.example.prospect.util.PessoaVersao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    private final FilaPessoaVersao filaPessoaVersao;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public PessoaFisicaService(FilaPessoaVersao filaPessoaVersao, PessoaFisicaRepository pessoaFisicaRepository) {
        this.filaPessoaVersao = filaPessoaVersao;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public List<PessoaFisica> getPessoasFisicas() {
        return this.pessoaFisicaRepository.findAll();
    }

    public PessoaFisica getPessoaFisica(long id) throws PessoaNotFoundException {
        Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository.findById(id);
        if (optionalPessoaFisica.isEmpty()) {
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        return optionalPessoaFisica.get();
    }

    public PessoaFisica addPessoaFisica(EntradaPessoaFisica pessoaFisica)
            throws PessoaConflictException {
        String cpf = pessoaFisica.getCpf();
        boolean exists = this.pessoaFisicaRepository.existsByCpf(cpf);
        if (exists) {
            throw new PessoaConflictException("Já existe pessoa física com CPF: " + cpf);
        }
        PessoaFisica savedPessoaFisica = this.pessoaFisicaRepository.save(pessoaFisica);
        PessoaVersao pessoaVersao = new PessoaVersao(
                savedPessoaFisica.getCpf(),
                savedPessoaFisica.getVersao()
        );
        filaPessoaVersao.inFila(pessoaVersao);
        return savedPessoaFisica;
    }

    public PessoaFisica deletePessoaFisica(long id) throws PessoaNotFoundException {
        Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository.findById(id);
        if (optionalPessoaFisica.isEmpty())
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        PessoaFisica pessoaFisica = optionalPessoaFisica.get();
        this.pessoaFisicaRepository.delete(pessoaFisica);
        return pessoaFisica;
    }

    public PessoaFisica updatePessoaFisica(
            EntradaPessoaFisica atualizaPessoaFisica, long id
    ) throws PessoaNotFoundException, PessoaConflictException {
        Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository.findById(id);
        if (optionalPessoaFisica.isEmpty())
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);

        String cpf = atualizaPessoaFisica.getCpf();
        if (
                !cpf.equals(optionalPessoaFisica.get().getCpf())
                && this.pessoaFisicaRepository.existsByCpf(cpf)
        ) {
            throw new PessoaConflictException("Já existe pessoa física com CPF: " + cpf);
        }
        atualizaPessoaFisica.setId(id);
        PessoaFisica savedPessoaFisica = this.pessoaFisicaRepository.save(atualizaPessoaFisica);
        PessoaVersao pessoaVersao = new PessoaVersao(
                savedPessoaFisica.getCpf(),
                savedPessoaFisica.getVersao()
        );
        filaPessoaVersao.inFila(pessoaVersao);
        return savedPessoaFisica;
    }
}
