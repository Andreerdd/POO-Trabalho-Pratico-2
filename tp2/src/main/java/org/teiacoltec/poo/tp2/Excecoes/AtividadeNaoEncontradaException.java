package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeNaoEncontradaException extends RuntimeException {
    public AtividadeNaoEncontradaException(String message, String idAtividade) {
        super("Erro! Atividade de id: %s \n Erro: %s".formatted( idAtividade, message));
    }
}
