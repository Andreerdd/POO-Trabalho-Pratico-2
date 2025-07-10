package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeJaAssociadaException extends Exception {
    public AtividadeJaAssociadaException(String nomeAtividade, String nomeTurma) {
        super("A atividade %s já esta associada a turma %s".formatted(nomeAtividade, nomeTurma));
    }
}
