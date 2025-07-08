package org.teiacoltec.poo.tp2.Escola;

import java.text.SimpleDateFormat; // Classe Date
import java.util.Date; // Classe SimpleDateFormat (para formatar a data)

public class Atividade {
    
    private int ID;
    private String Nome;
    private String Descricao;
    private Date Inicio;
    private Date Fim;
    private float Pontos;

    public Atividade(int id, String nome, String descricao, Date inicio, Date fim, float pontos) {
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;
        this.Pontos = pontos;
    }

    // Obtém as informações da atividade
    public String ObterInformacoes() {

        // Formata a data de nascimento
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String inicioFormatado = formato.format(this.Inicio);
        String fimFormatado = formato.format(this.Fim);

        return   "|| Atividade: " + this.Nome 
             + "\n|| ID: " + this.ID
             + "\n|| Descricao:\n\t" + this.Descricao
             + "\n|| Pontos: " + this.Pontos
             + "\n|| Data de Inicio: " + inicioFormatado 
             + "\n|| Data de Fim: " + fimFormatado;
    }

    // Sets
    public void setNome(String nome) {
        this.Nome = nome;
    }

    public void setDescricao(String descricao) {
        this.Descricao = descricao;
    }

    public void setInicio(Date inicio) {
        this.Inicio = inicio;
    }

    public void setFim(Date fim) {
        this.Fim = fim;
    }

    public void setPontos(float pontos) {
        this.Pontos = pontos;
    }

    // Gets
    public String getNome() {
        return this.Nome;
    }

    public String getDescricao() {
        return this.Descricao;
    }

    public Date getInicio() {
        return this.Inicio;
    }

    public Date getFim() {
        return this.Fim;
    }

    public float getPontos() {
        return this.Pontos;
    }

    public int getId() {
        return this.ID;
    }

}
