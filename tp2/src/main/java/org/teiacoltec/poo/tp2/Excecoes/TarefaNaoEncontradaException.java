package org.teiacoltec.poo.tp2.Excecoes;

public class TarefaNaoEncontradaException extends Exception {
    public TarefaNaoEncontradaException(String message, int idAtividade) {
        super("Erro! Tarefa de id: %s \n Erro: %s".formatted( idAtividade, message));
    }
}
