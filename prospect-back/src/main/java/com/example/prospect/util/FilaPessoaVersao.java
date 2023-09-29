package com.example.prospect.util;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class FilaPessoaVersao {

    private Queue<PessoaVersao> fila = new LinkedList<>();

    public void inFila(PessoaVersao item) {
        fila.offer(item);
    }

    public PessoaVersao outFila() {
        return fila.poll();
    }

    public PessoaVersao peekFila() {
        return this.fila.peek();
    }
}
