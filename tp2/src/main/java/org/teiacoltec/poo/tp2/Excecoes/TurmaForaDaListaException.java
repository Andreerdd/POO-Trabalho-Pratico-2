package org.teiacoltec.poo.tp2.Excecoes;

public class TurmaForaDaListaException extends Exception {

    public TurmaForaDaListaException(String pessoa) {
        super("A turma " + pessoa + " ja esta fora da lista de turma");
    }
  
}
