package com.example.prospect.entity.container;

import com.example.prospect.entity.PessoaFisica;
import com.example.prospect.entity.PessoaJuridica;

public class PessoaContainer {
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;

    public PessoaContainer(PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica) {
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
}
