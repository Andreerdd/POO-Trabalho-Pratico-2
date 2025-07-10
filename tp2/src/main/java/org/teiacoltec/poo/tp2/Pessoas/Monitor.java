/**
 * Classe responsável por representar um monitor.
 */
package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date;

public class Monitor extends Pessoa {
    private final String matricula;
    private final String curso;

    public Monitor(String cpf, String nome, Date nascimento, String email, String endereco, String matricula, String curso) {
        super(cpf, nome, nascimento, email, endereco);
        this.matricula = matricula;
        this.curso = curso;
    }
    
    // Getters //
    public String getMatricula() {
        return matricula;
    }
    public String getCurso() {
        return curso;
    }
}
