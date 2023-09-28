package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaFisicaRepository;
import com.example.prospect.repository.PessoaJuridicaRepository;
import com.example.prospect.util.FilaPessoaVersao;
import com.example.prospect.util.PessoaVersao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilaPessoaVersaoService {

    private final FilaPessoaVersao filaPessoaVersao;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    public FilaPessoaVersaoService(FilaPessoaVersao filaPessoaVersao, PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.filaPessoaVersao = filaPessoaVersao;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    public PessoaVersao retiraPessoaVersao() {
        PessoaVersao pessoaVersao = filaPessoaVersao.outFila();
        if (pessoaVersao == null) {
            throw new PessoaNotFoundException("Fila está vazia");
        }

        String cadastro = pessoaVersao.getCadastro();
        Optional<PessoaFisica> pessoaFisica =
                pessoaFisicaRepository.findPessoaFisicaByCpf(cadastro);
        if (pessoaFisica.isEmpty()) {
            Optional<PessoaJuridica> pessoaJuridica =
                    pessoaJuridicaRepository.findPessoaJuridicaByCnpj(cadastro);
            if (pessoaJuridica.isEmpty()) {
                throw new PessoaNotFoundException("Pessoa não encontrada com CPF/CNPJ: " + cadastro);
            }
        }

        return pessoaVersao;
    }
}
