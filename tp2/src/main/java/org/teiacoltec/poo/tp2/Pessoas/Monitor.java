/*
 * Classe responsável por representar um monitor.
 */
package org.teiacoltec.poo.tp2.Pessoas;

public class Monitor {
    private final String matricula;
    private final String curso;

    public Monitor(String matricula, String curso) {
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
