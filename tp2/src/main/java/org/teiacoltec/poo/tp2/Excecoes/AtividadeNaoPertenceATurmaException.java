package org.teiacoltec.poo.tp2.Excecoes;

public class AtividadeNaoPertenceATurmaException extends Exception {
    public AtividadeNaoPertenceATurmaException(String nomeAtividade, String nomeTurma) {
        super("A atividade %s nao pertence a turma %s".formatted(nomeAtividade, nomeTurma));
    }
}
