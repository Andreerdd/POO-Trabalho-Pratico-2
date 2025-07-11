package org.teiacoltec.poo.tp2.Excecoes;

public class TarefaJaCriadaException extends Exception {
    public TarefaJaCriadaException(int idAtividade, String nomeAluno) {
        super("Erro! Tarefa de id %d ja foi criada para o aluno %s!".formatted(idAtividade, nomeAluno));
    }
}
