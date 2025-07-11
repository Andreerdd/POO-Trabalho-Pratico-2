package org.teiacoltec.poo.tp2.Excecoes;

public class TurmaNaoEncontradaException extends Exception {
    public TurmaNaoEncontradaException(int idAtividade) {
        super("Erro! Turma de id %d nao foi encontrada!".formatted(idAtividade));
    }
    public TurmaNaoEncontradaException(String message) {
        super(message);
    }
}
