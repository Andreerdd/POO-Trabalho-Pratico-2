/**
 * Classe que representa uma tarefa, que é uma
 * atividade a ser realizada por um aluno.
 */

package org.teiacoltec.poo.tp2.Escola;

import java.util.Date;
import java.util.HashMap; // Classe HashMap (para armazenar tarefas)
import java.util.LinkedList; // Classe LinkedList (para armazenar tarefas)

import org.teiacoltec.poo.tp2.Excecoes.TarefaJaCriadaException;
import org.teiacoltec.poo.tp2.Excecoes.TarefaNaoEncontradaException;
import org.teiacoltec.poo.tp2.Pessoas.Aluno;
import org.teiacoltec.poo.tp2.Utils;


public class Tarefa {
    // Constantes //
    private static final float NOTA_INICIAL = 0.0f; // Nota inicial da tarefa

    // Todas as tarefas existentes
    private static final HashMap<Integer, Tarefa> Tarefas = new HashMap<>();

    private final int ID;
    private final Aluno Aluno;
    private final Turma Turma;
    private final Atividade Atividade;
    private float Nota = NOTA_INICIAL;

    // Construtor com id
    public Tarefa(int id, Aluno aluno, Turma turma, Atividade atividade) {
        this.ID = id;
        this.Aluno = aluno;
        this.Turma = turma;
        this.Atividade = atividade;

        // Adiciona a tarefa ao HashMap
        Tarefas.put(id, this);
    }

    // Construtor sem id
    public Tarefa(Aluno aluno, Turma turma, Atividade atividade) {
        int id = Tarefas.size() + 1; // Gera um novo ID baseado no tamanho atual do HashMap
        this.ID = id;
        this.Aluno = aluno;
        this.Turma = turma;
        this.Atividade = atividade;

        // Adiciona a tarefa ao HashMap
        Tarefas.put(id, this);
    }

    /**
     * Obtém uma tarefa pelo ID dela.
     * @param id O ID da tarefa a ser obtida.
     * @return A tarefa correspondente ao ID.
     * @throws TarefaNaoEncontradaException Se a tarefa não for encontrada.
     */
    public static Tarefa obtemTarefaPorId(int id) throws TarefaNaoEncontradaException {
        // Verifica se a tarefa existe no HashMap
        if (Tarefas.containsKey(id)) {
            return Tarefas.get(id);
        } else {
            throw new TarefaNaoEncontradaException(id);
        }
    }


    /**
     * Obter todas as tarefas da pessoa a partir dela.
     *
     * @param aluno O aluno que será usado para obter as tarefas.
     * @return Uma lista de tarefas associadas à pessoa.
     */
    public static LinkedList<Tarefa> obterTarefasDaPessoa(Aluno aluno) {
        LinkedList<Tarefa> tarefas = new LinkedList<>();

        // Percorre todas as tarefas e verifica se o aluno é a pessoa
        for (Tarefa tarefa : Tarefas.values()) {
            // Verifica se a tarefa pertence ao aluno
            if (tarefa.Aluno.equals(aluno)) {
                // Se for, adiciona à lista de tarefas
                tarefas.add(tarefa);
            }
        }

        // Retorna o que encontrar
        return tarefas;
    }

    /**
     * Obtém as tarefas de um aluno em um período específico.
     *
     * @param aluno O aluno a obter as tarefas.
     * @param inicio A data de início do período.
     * @param fim A data de fim do período
     * @return Uma lista de tarefas do aluno no período especificado.
     */
    public static LinkedList<Tarefa> obterTarefasDaPessoa(Aluno aluno, Date inicio, Date fim) {
        LinkedList<Tarefa> tarefas = new LinkedList<>();

        // Percorre todas as tarefas e verifica se o aluno é a pessoa
        for (Tarefa tarefa : Tarefas.values()) {
            // Verifica se a tarefa pertence ao aluno
            if (tarefa.Aluno.equals(aluno)) {
                // Verifica se a data da tarefa está dentro do período especificado
                // (não antes do início e não depois do fim)
                if (Utils.dataNoPeriodo(tarefa.Atividade.getInicio(), tarefa.Atividade.getFim(), inicio, fim)) {
                    // Se for, adiciona à lista de tarefas
                    tarefas.add(tarefa);
                }
            }
        }

        // Retorna o que encontrar
        return tarefas;
    }

    public static void criarTarefaParaAluno(Aluno aluno, Turma turma, Atividade atividade) throws TarefaJaCriadaException {
        // Verifica se já existe uma tarefa para o aluno e a atividade
        for (Tarefa tarefa : Tarefas.values()) {
            if (tarefa.Aluno.equals(aluno) && tarefa.Atividade.equals(atividade)) {
                throw new TarefaJaCriadaException(atividade.getId(), aluno.getNome());
            }
        }

        // Cria a tarefa
        Tarefa tarefa = new Tarefa(aluno, turma, atividade);
    }

    /**
     * Obtém as informacoes da tarefa.
     *
     * @return Uma string com as informações da tarefa.
     */
    public String ObterInformacoes() {
        return    "|| ------ Informacoes da Atividade ------"
                + "\n|| ID: " + this.Atividade.getId()
                + "\n|| Nome: " + this.Atividade.getNome()
                + "\n|| Descricao: " + this.Atividade.getDescricao()
                + "|| ------ Informacoes da Tarefa ------ "
                + "\n|| ID: " + this.ID
                + "\n|| Turma: " + this.Turma.getNome()
                + "\n|| Aluno:\n\t" + this.Aluno.getNome()
                + "\n|| Nota: " + this.Nota;
    }

    public String toString() {
        return "\n|| Nome da Atividade: " + this.Atividade.getNome()
                + "\n|| ID da Atividade: " + this.Atividade.getId()
                + "\n|| Turma: " + this.Turma.getNome()
                + "\n|| Nota: " + this.Nota;
    }
    // Atualizar a nota da tarefa
    public void atualizarNota(float novaNota) {this.Nota = novaNota;}
    // Obter a nota da tarefa
    public float obterNota() {return this.Nota;}
    // Obter o ID da tarefa
    public int getID() {return this.ID;}

    // Setters //
    public void setNota(float nota) {this.Nota = nota;}
}
