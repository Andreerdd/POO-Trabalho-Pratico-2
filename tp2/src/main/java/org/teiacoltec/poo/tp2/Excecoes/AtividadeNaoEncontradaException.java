package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeNaoEncontradaException extends Exception {
    public AtividadeNaoEncontradaException(String message, int idAtividade) {
        super("Erro! Atividade de id: %d \n Erro: %s".formatted( idAtividade, message));
    }
    public AtividadeNaoEncontradaException(String nomeAtividade, String nomeTurma) {
        super("A atividade %s nao esta associada a turma %s".formatted( nomeAtividade, nomeTurma));
    }
    public AtividadeNaoEncontradaException(int idAtividade, String nomeAtividade) {
        super("A atividade de nome %s (ID: %d) nao foi encontrada".formatted(nomeAtividade, idAtividade));
    }
    public AtividadeNaoEncontradaException(int idAtividade) {
        super("A atividade de ID %d nao foi encontrada".formatted(idAtividade));
    }
}
