package org.teiacoltec.poo.tp2.Excecoes;

public class TarefaNaoEncontradaException extends Exception {
    public TarefaNaoEncontradaException(int idAtividade) {
        super("Erro! Tarefa de id %d nao foi encontrada!".formatted( idAtividade));
    }
}
