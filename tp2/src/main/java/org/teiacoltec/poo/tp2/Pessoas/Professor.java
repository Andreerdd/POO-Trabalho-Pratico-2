package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date;

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
