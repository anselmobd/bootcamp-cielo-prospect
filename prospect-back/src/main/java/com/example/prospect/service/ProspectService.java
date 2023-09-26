package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProspectService {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public ProspectService(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public List<PessoaFisica> getPessoasFisicas() {
        return this.pessoaFisicaRepository.findAll();
    }

    public PessoaFisica getPessoaFisica(long id) {
        Optional<PessoaFisica> pessoaFisica = this.pessoaFisicaRepository.findById(id);

        if (pessoaFisica.isEmpty()) {
            throw new IllegalStateException("id não encontrado: " + id);
        }
        return pessoaFisica.get();
    }

    public void addPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisicaRepository.save(pessoaFisica);
    }

    public void deletePessoaFisica(long id) {
        boolean exists = this.pessoaFisicaRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("id não encontrado: " + id);
        }
        this.pessoaFisicaRepository.deleteById(id);
    }
}
