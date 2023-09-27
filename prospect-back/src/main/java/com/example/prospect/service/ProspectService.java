package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        return pessoaFisica.get();
    }

    public PessoaFisica addPessoaFisica(PessoaFisica pessoaFisica) {
        String cpf = pessoaFisica.getCpf();
        boolean exists = this.pessoaFisicaRepository.existsByCpf(cpf);
        if (exists) {
            throw new IllegalStateException("Já existe pessoa física com CPF: " + cpf);
        }
        try {
            return this.pessoaFisicaRepository.save(pessoaFisica);
        } catch (Exception e) {
            String messageError;
            messageError = "Erro ao criar pessoa física com os dados informados, causa: " +
                    e.getMessage();
            throw new IllegalStateException(messageError, e);
        }
    }

    public void deletePessoaFisica(long id) {
        boolean exists = this.pessoaFisicaRepository.existsById(id);
        if (!exists) {
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        this.pessoaFisicaRepository.deleteById(id);
    }

    public PessoaFisica updatePessoaFisica(@RequestBody PessoaFisica pessoaFisica, @PathVariable long id) {
        Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository.findById(id);
        if (optionalPessoaFisica.isEmpty())
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);

        if (pessoaFisica.getCpf() != null)
            optionalPessoaFisica.get().setCpf(pessoaFisica.getCpf());
        if (pessoaFisica.getMcc() != null)
            optionalPessoaFisica.get().setMcc(pessoaFisica.getMcc());
        if (pessoaFisica.getNome() != null)
            optionalPessoaFisica.get().setNome(pessoaFisica.getNome());
        if (pessoaFisica.getEmail() != null)
            optionalPessoaFisica.get().setEmail(pessoaFisica.getEmail());

        return this.pessoaFisicaRepository.save(optionalPessoaFisica.get());
    }
}
