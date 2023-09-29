package com.example.prospect.service;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.entity.PessoaJuridica;
import com.example.prospect.entity.superclass.PessoaSuperclass;
import com.example.prospect.exception.PessoaNotFoundException;
import com.example.prospect.repository.PessoaFisicaRepository;
import com.example.prospect.repository.PessoaJuridicaRepository;
import com.example.prospect.util.FilaPessoaVersao;
import com.example.prospect.util.PessoaVersao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public PessoaSuperclass retiraPessoaVersao() {
        PessoaVersao pessoaVersao;

        // Consome a fila até achar uma entrada válida ou esvaziar a fila
        while (true) {
            pessoaVersao = filaPessoaVersao.outFila();
            if (pessoaVersao == null) {
                throw new PessoaNotFoundException("Fila está vazia");
            }

            String cadastro = pessoaVersao.getCadastro();
            Date versao = pessoaVersao.getVersao();
            if (cadastro.length() == 11) {
                Optional<PessoaFisica> optionalPessoaFisica =
                        pessoaFisicaRepository.findPessoaFisicaByCpf(cadastro);
                if (optionalPessoaFisica.isPresent()) {
                    PessoaFisica pessoaFisica = optionalPessoaFisica.get();
                    if (versao.equals(pessoaFisica.getVersao())) {
                        return pessoaFisica;
                    }
                }
            } else {
                Optional<PessoaJuridica> optionalPessoaJuridica =
                        pessoaJuridicaRepository.findPessoaJuridicaByCnpj(cadastro);
                if (optionalPessoaJuridica.isPresent()) {
                    PessoaJuridica pessoaJuridica = optionalPessoaJuridica.get();
                    if (versao.equals(pessoaJuridica.getVersao())) {
                        return pessoaJuridica;
                    }
                }
            }
        }
    }

    public PessoaVersao peekPessoaVersao() {
        PessoaVersao pessoaVersao = filaPessoaVersao.peekFila();
        if (pessoaVersao == null) {
            throw new PessoaNotFoundException("Fila está vazia");
        }
        return pessoaVersao;
    }
}
