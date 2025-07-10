package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date;

import org.teiacoltec.poo.tp2.Entrada;

public class Professor extends Pessoa {

    private String Matricula;
    private String Formacao;

    public Professor(String cpf, String nome, Date nascimento, String email, String endereco, String matricula, String formacao) {
        super(cpf, nome, nascimento, email, endereco);
        this.Matricula = matricula;
        this.Formacao = formacao;
    }

    // Obtém as informações do professor
    @Override
    public String ObterInformacoes() {
        return super.ObterInformacoes() 
            + "\n|| Matricula: " + this.Matricula 
            + "\n|| Formacao: " + this.Formacao;
    }
    
     /*
     * Cria um novo professor
     * 
     * @param ... informações do professor
     * @return o professor criado 
     */
    public static Professor criarProfessor(String cpf, String nome, Date nascimento, String email, String endereco) {
        Professor novo;

        // Obtém as informações & cria um novo aluno
        String matricula = Entrada.lerString("Matricula do professor");
        String curso = Entrada.lerString("Curso do professor");

        novo = new Professor(cpf, nome, nascimento, email, endereco, matricula, curso);

        // Retorna o aluno criado
        return novo;
    }

    // Sets
    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }

    public void setFormacao(String formacao) {
        this.Formacao = formacao;
    }

    // Gets
    public String getMatricula() {
        return this.Matricula;
    }

    public String getFormacao() {
        return this.Formacao;
    }
}
