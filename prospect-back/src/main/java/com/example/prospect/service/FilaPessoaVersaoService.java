package com.example.prospect.service;

import com.example.prospect.util.PessoaVersao;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class FilaPessoaVersaoService {

    private Queue<PessoaVersao> queue = new LinkedList<>();

    public void inFila(PessoaVersao item) {
        queue.offer(item);
    }

    public PessoaVersao outFila() {
        return queue.poll();
    }
}
