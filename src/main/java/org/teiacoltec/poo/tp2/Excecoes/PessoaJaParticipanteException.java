package org.teiacoltec.poo.tp2.Excecoes;

public class PessoaJaParticipanteException extends Exception {
    
    public PessoaJaParticipanteException(String nomePessoa, String nomeTurma) {
        super("A pessoa " + nomePessoa + " ja esta participando da turma " + nomeTurma);
    }
  
}
