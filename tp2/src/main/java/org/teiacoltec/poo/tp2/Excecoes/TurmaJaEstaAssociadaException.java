package org.teiacoltec.poo.tp2.Excecoes;

public class TurmaJaEstaAssociadaException extends Exception {

    public TurmaJaEstaAssociadaException(String nomeTurma, String nomeSubturma) {
        super("A turma " + nomeSubturma + " ja esta associada a turma " + nomeTurma);
    }
    
}
