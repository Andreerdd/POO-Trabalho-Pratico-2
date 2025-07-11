package org.teiacoltec.poo.tp2.Excecoes;

public class AlunoNaoEncontradoException extends Exception {

    public AlunoNaoEncontradoException(String matricula) {
        super("O aluno com matricula %s nao foi encontrado.".formatted(matricula));
    }
  
}
