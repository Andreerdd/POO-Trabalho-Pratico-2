package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeNaoEncontradaException extends Exception {
    public AtividadeNaoEncontradaException(String message, int idAtividade) {
        super("Erro! Atividade de id: %s \n Erro: %s".formatted( idAtividade, message));
    }
    public AtividadeNaoEncontradaException(String nomeAtividade, String nomeTurma) {
        super("A atividade %s nao esta associada a turma %s".formatted( nomeAtividade, nomeTurma));
    }
}
