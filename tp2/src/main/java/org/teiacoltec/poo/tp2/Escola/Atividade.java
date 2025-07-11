/*
 * Classe responsável por representar uma atividade.
 */
package org.teiacoltec.poo.tp2.Escola;


import java.text.SimpleDateFormat; // Classe HashMap (para armazenar atividades)
import java.util.Date; // Classe Date
import java.util.HashMap; // Classe SimpleDateFormat (para formatar a data)
import java.util.LinkedList;

import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Excecoes.AtividadeNaoEncontradaException;

public class Atividade {
    // ID proibido para evitar conflitos
    public static final int ID_PROIBIDO = 0;

    // Todas as atividades existentes
    private static final HashMap<Integer, Atividade> Atividades = new HashMap<>();

    private final int ID;
    private String Nome;
    private String Descricao;
    private Date Inicio;
    private Date Fim;
    private float Valor;

    // Construtor com ID
    public Atividade(int id, String nome, String descricao, Date inicio, Date fim, float valor) {
        this.ID = id;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Inicio = inicio;
        this.Fim = fim;
        this.Valor = valor;
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
        this.Valor = valor;
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
    public static Atividade obtemAtividadePorId(int id) throws AtividadeNaoEncontradaException {
        // Verifica se a atividade existe
        if (Atividades.containsKey(id)) return Atividades.get(id);

        // Se chegou até aqui, é porque a atividade não foi encontrada
        throw new AtividadeNaoEncontradaException("Nao encontrou a atividade de ID", id);
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
             + "\n|| Valor: " + this.Valor
             + "\n|| Data de Inicio: " + inicioFormatado 
             + "\n|| Data de Fim: " + fimFormatado;
    }

    /**
     * Cria uma nova atividade com as informações fornecidas pelo usuário.
     *
     * @return A nova atividade criada.
     */
    public static Atividade criarAtividade() {
        // Pede as informações da turma
        String nome = Entrada.lerString("Digite o nome da atividade");
        String descricao = Entrada.lerString("Digite a descricao da atividade");
        Date inicio = Entrada.lerData("Digite a data de inicio da atividade");
        Date fim = Entrada.lerData("Digite a data de fim da atividade");
        float valor = Entrada.lerFloat("Digite o Valor da atividade");

        // Pergunta se o usuário quer criar uma atividade com ID ou sem ID
        int id;

        String resposta = Entrada.lerString("Deseja definir o ID da atividade? (S/N)", new String[]{"S", "N"}).toUpperCase();
        if (resposta.equals("S")) {
            // Se sim, pede o ID
            int[] ids = Atividade.obterTodosIdsAtividades();
            id = Entrada.lerInteiroExceto("Digite o ID da Atividade", ids);
        } else {
            // Se não, define o ID automaticamente
            id = 0;

            // Incrementa o ID até encontrar um que não esteja em uso
            do {
                id++;
            } while (Atividades.containsKey(id)); // Verifica se o ID já está em uso
        }

        return new Atividade(id, nome, descricao, inicio, fim, valor);
    }

    /**
     * Obtém todos os IDs das atividades existentes.
     *
     * @return Um array contendo todos os IDs das atividades.
     */
    public static int[] obterTodosIdsAtividades() {
        // Cria uma lista para armazenar os IDs das atividades
        LinkedList<Integer> idsAtividades = new LinkedList<>();

        // Adiciona o ID proibido
        idsAtividades.add(ID_PROIBIDO);

        // Adiciona o ID de cada turma à atividade
        for (Atividade atividade : Atividades.values()) {
            idsAtividades.add(atividade.getId());
        }

        // Converte a lista para um array e retorna
        // Obs.: poderia ter feito um for, por exemplo,
        //       mas assim é mais elegante :)
        return idsAtividades.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Apaga uma atividade da lista de atividades.
     *
     * @param atividade A atividade a ser apagada.
     * @throws AtividadeNaoEncontradaException se a atividade não for encontrada
     */
    public static void apagarAtividadeDaLista(Atividade atividade) throws AtividadeNaoEncontradaException {
        int id = atividade.getId();
        // Verifica se a atividade existe
        if (Atividades.containsKey(id)) {
            // Desassocia as tarefas
            Tarefa.desassociarAtividade(atividade);
            // Remove a atividade
            Atividades.remove(id);
        } else {
            // Se não existir, lança uma exceção
            throw new AtividadeNaoEncontradaException(id, atividade.getNome());
        }
    }

    // Verifica se o ID é o mesmo
    public boolean equals(Atividade atividade) {
        return this.ID == atividade.getId();
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

    public void setValor(float valor) {
        this.Valor = valor;
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

    public float getValor() {
        return this.Valor;
    }

    public int getId() {
        return this.ID;
    }

    public static HashMap<Integer, Atividade> getAtividades() { return Atividades; }

}
