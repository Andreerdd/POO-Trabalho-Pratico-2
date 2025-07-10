package org.teiacoltec.poo.tp2.Excecoes;

public class PessoaNaoEncontradaException extends Exception {
    
    public PessoaNaoEncontradaException(String nomePessoa, String nomeTurma) {
        super("A pessoa " + nomePessoa + " nao esta na turma " + nomeTurma);
    }
  
}
