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

    public PessoaVersao retiraPessoaVersao() {
        boolean buscaNaFila = true;
        PessoaVersao pessoaVersao = null;

        while (buscaNaFila) {
            pessoaVersao = filaPessoaVersao.outFila();
            if (pessoaVersao == null) {
                throw new PessoaNotFoundException("Fila está vazia");
            }

            String cadastro = pessoaVersao.getCadastro();
            Date versao = pessoaVersao.getVersao();

            Optional<PessoaFisica> pessoaFisica =
                    pessoaFisicaRepository.findPessoaFisicaByCpf(cadastro);
            if (pessoaFisica.isPresent()) {
                if (versao.equals(pessoaFisica.get().getVersao())) {
                    buscaNaFila = false;
                }
            } else {
                Optional<PessoaJuridica> pessoaJuridica =
                        pessoaJuridicaRepository.findPessoaJuridicaByCnpj(cadastro);
                if (pessoaJuridica.isPresent()) {
                    if (versao.equals(pessoaJuridica.get().getVersao())) {
                        buscaNaFila = false;
                    }
                } else {
                    throw new PessoaNotFoundException("Pessoa não encontrada com CPF/CNPJ: " + cadastro);
                }
            }
        }

        return pessoaVersao;
    }
}
