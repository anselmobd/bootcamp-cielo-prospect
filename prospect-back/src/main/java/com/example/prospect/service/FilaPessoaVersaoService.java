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
import java.util.LinkedList;
import java.util.List;
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

    public List<PessoaSuperclass> retiraPessoaVersao() {
//        boolean pegaProximoNaFila = true;
        PessoaVersao pessoaVersao;
        List<PessoaSuperclass> listPessoa = new LinkedList<>();

//        while (pegaProximoNaFila) {
        while (true) {
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
//                    pegaProximoNaFila = false;
//                    return new PessoaContainer(pessoaFisica.get(), null);
                    listPessoa.add(pessoaFisica.get());
                    return listPessoa;
                }
            } else {
                Optional<PessoaJuridica> pessoaJuridica =
                        pessoaJuridicaRepository.findPessoaJuridicaByCnpj(cadastro);
                if (pessoaJuridica.isPresent()) {
                    if (versao.equals(pessoaJuridica.get().getVersao())) {
//                        pegaProximoNaFila = false;
//                        return new PessoaContainer(null, pessoaJuridica.get());
                        listPessoa.add(pessoaJuridica.get());
                        return listPessoa;
                    }
                }
            }
        }
//        return pessoaVersao;
    }
}
