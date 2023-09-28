package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.entity.input.EntradaPessoaFisica;
import com.example.prospect.exception.PessoaConflictException;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository) {
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
        return this.pessoaFisicaRepository.save(pessoaFisica);
    }

    public void deletePessoaFisica(long id) throws PessoaNotFoundException {
        boolean exists = this.pessoaFisicaRepository.existsById(id);
        if (!exists) {
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        this.pessoaFisicaRepository.deleteById(id);
    }

    public PessoaFisica updatePessoaFisica(
            @RequestBody EntradaPessoaFisica atualizaPessoaFisica, @PathVariable long id
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
        return this.pessoaFisicaRepository.save(atualizaPessoaFisica);
    }
}
