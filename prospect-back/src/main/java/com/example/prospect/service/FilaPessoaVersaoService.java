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
                Optional<PessoaFisica> opcionalPessoaFisica =
                        pessoaFisicaRepository.findPessoaFisicaByCpf(cadastro);
                if (opcionalPessoaFisica.isPresent()) {
                    PessoaFisica pessoaFisica = opcionalPessoaFisica.get();
                    if (versao.equals(pessoaFisica.getVersao())) {
                        return pessoaFisica;
                    }
                }
            } else {
                Optional<PessoaJuridica> opcionalPessoaJuridica =
                        pessoaJuridicaRepository.findPessoaJuridicaByCnpj(cadastro);
                if (opcionalPessoaJuridica.isPresent()) {
                    PessoaJuridica pessoaJuridica = opcionalPessoaJuridica.get();
                    if (versao.equals(pessoaJuridica.getVersao())) {
                        return pessoaJuridica;
                    }
                }
            }
        }
    }
}
