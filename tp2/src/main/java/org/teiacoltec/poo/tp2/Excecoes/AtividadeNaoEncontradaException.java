package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeNaoEncontradaException extends Exception {
    public AtividadeNaoEncontradaException(String message, int idAtividade) {
        super("Erro! Atividade de id: %s \n Erro: %s".formatted( idAtividade, message));
    }
}
