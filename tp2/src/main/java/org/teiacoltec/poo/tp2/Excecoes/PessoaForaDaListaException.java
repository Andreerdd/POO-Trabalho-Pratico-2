package org.teiacoltec.poo.tp2.Excecoes;

public class PessoaForaDaListaException extends Exception {
    
    public PessoaForaDaListaException(String pessoa) {
        super("A pessoa " + pessoa + " ja esta fora da lista de pessoas");
    }
  
}
