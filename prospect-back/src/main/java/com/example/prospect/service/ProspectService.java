package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public PessoaFisica getPessoaFisica(@PathVariable long id) {
        Optional<PessoaFisica> pessoaFisica = this.pessoaFisicaRepository.findById(id);

//        if (pessoaFisica.isEmpty())
//            throw new PessoaFisicaNotFoundException("id-" + id);

        return pessoaFisica.get();
    }

}
