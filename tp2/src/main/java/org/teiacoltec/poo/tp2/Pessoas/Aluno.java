package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date;

import org.teiacoltec.poo.tp2.Entrada;

public class Aluno extends Pessoa {

    private String Matricula;
    private String Curso;

    public Aluno(String cpf, String nome, Date nascimento, String email, String endereco, String matricula, String curso) {
        super(cpf, nome, nascimento, email, endereco);
        this.Matricula = matricula;
        this.Curso = curso;
    }

    // Obtém as informações do aluno
    @Override
    public String ObterInformacoes() {
        return super.ObterInformacoes() 
            + "\n|| Matricula: " + this.Matricula 
            + "\n|| Curso: " + this.Curso;
    }

    /*
     * Cria um novo aluno
     * 
     * @return o aluno criado 
     */
    public static Aluno criarAluno(String cpf, String nome, Date nascimento, String email, String endereco) {
        Aluno novo;

        // Obtém as informações & cria um novo aluno
        String matricula = Entrada.lerString("Matricula do aluno");
        String curso = Entrada.lerString("Curso do aluno");

        novo = new Aluno(cpf, nome, nascimento, email, endereco, matricula, curso);

        // Retorna o aluno criado
        return novo;
    }

    // Sets
    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }

    public void setCurso(String curso) {
        this.Curso = curso;
    }

    // Gets
    public String getMatricula() {
        return this.Matricula;
    }

    public String getCurso() {
        return this.Curso;
    }
    
}
