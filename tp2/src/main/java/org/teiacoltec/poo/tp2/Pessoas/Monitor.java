/**
 * Classe responsável por representar um monitor.
 */
package org.teiacoltec.poo.tp2.Pessoas;

import org.teiacoltec.poo.tp2.Entrada;

import java.util.Date;

public class Monitor extends Pessoa implements Matriculado {
    private String Matricula;
    private String Curso;

    public Monitor(String cpf, String nome, Date nascimento, String email, String endereco, String matricula, String curso) {
        super(cpf, nome, nascimento, email, endereco);
        this.Matricula = matricula;
        this.Curso = curso;
    }

    // Obtém as informações do aluno
    @Override
    public String ObterInformacoes() {
        return super.ObterInformacoes("Monitor")
                + "\n|| Matricula: " + this.Matricula
                + "\n|| Curso: " + this.Curso;
    }

    /*
     * Cria um novo monitor
     *
     * @param ... informações do monitor
     * @return o monitor criado
     */
    public static Monitor criarMonitor(String cpf, String nome, Date nascimento, String email, String endereco, String[] matriculas) {
        Monitor novo;

        // Obtém as informações & cria um novo monitor
        String matricula = Entrada.lerStringExceto("Matricula do monitor", matriculas);
        String curso = Entrada.lerString("Curso do monitor");

        novo = new Monitor(cpf, nome, nascimento, email, endereco, matricula, curso);

        // Retorna o monitor criado
        return novo;
    }

    // Setters //
    public void setMatricula(String matricula) {this.Matricula = matricula;}
    public void setCurso(String curso) {this.Curso = curso;}
    // Getters //
    public String getMatricula() {
        return Matricula;
    }
    public String getCurso() {
        return Curso;
    }
}
