package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeNaoAssociadaException extends Exception {
    public AtividadeNaoAssociadaException(String nomeAtividade, String nomeTurma) {
        super("A atividade %s nao esta associada a turma %s".formatted(nomeAtividade, nomeTurma));
    }
}
