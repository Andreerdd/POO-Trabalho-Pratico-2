/*
 * Classe responsável por representar uma atividade.
 */
package org.teiacoltec.poo.tp2.Escola;


import java.text.SimpleDateFormat; // Classe HashMap (para armazenar atividades)
import java.util.Date; // Classe Date
import java.util.HashMap; // Classe SimpleDateFormat (para formatar a data)

import org.teiacoltec.poo.tp2.Excecoes.AtividadeNaoEncontradaException;

public class Atividade {
    // Todas as atividades existentes
    private static final HashMap<Integer, Atividade> Atividades = new HashMap<>();

    private final int ID;
    private String Nome;
    private String Descricao;
    private Date Inicio;
    private Date Fim;
    private float valor;

    // Construtor com ID
    public Atividade(int id, String nome, String descricao, Date inicio, Date fim, float valor) {
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;
        this.valor = valor;
        // Adiciona a atividade ao mapa de atividades
        Atividades.put(id, this);
    }

    // Construtor sem ID, gera um ID único automaticamente
    public Atividade(String nome, String descricao, Date inicio, Date fim, float valor) {
        int id = Atividades.size() + 1; // Gera um ID único baseado no tamanho atual do mapa
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;
        this.valor = valor;
        // Adiciona a atividade ao mapa de atividades
        Atividades.put(id, this);
    }

    /**
     * Obtém uma atividade pelo ID dela.
     *
     * @param id O ID da atividade a ser obtida.
     * @return A atividade correspondente ao ID.
     * @throws AtividadeNaoEncontradaException se a atividade não for encontrada
     */
    public Atividade obtemAtividadePorId(int id) throws AtividadeNaoEncontradaException {
        // Verifica se a atividade existe
        if (Atividades.containsKey(id)) return Atividades.get(id);

        // Se chegou até aqui, é porque a atividade não foi encontrada
        throw new AtividadeNaoEncontradaException("Nao encontrou a atividade na lista da turma " + this.getNome(), id);
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
             + "\n|| Valor: " + this.valor
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

    public void setPontos(float valor) {
        this.valor = valor;
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
        return this.valor;
    }

    public int getId() {
        return this.ID;
    }

}
