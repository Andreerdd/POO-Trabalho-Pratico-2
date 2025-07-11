/**
 * Classe que representa uma tarefa, que é uma
 * atividade a ser realizada por um aluno.
 */

package org.teiacoltec.poo.tp2.Escola;

import java.util.Date;
import java.util.HashMap; // Classe HashMap (para armazenar tarefas)
import java.util.LinkedList; // Classe LinkedList (para armazenar tarefas)

import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Excecoes.*;
import org.teiacoltec.poo.tp2.Pessoas.Aluno;
import org.teiacoltec.poo.tp2.Utils;


public class Tarefa {
    // Constantes //
    private static final float NOTA_INICIAL = 0.0f; // Nota inicial da tarefa

    // o "0" pode ser usado para outras coisas
    private static final int ID_PROIBIDO = 0;

    // Todas as tarefas existentes
    private static final HashMap<Integer, Tarefa> Tarefas = new HashMap<>();

    private final int ID;
    private final Aluno aluno;
    private final Turma turma;
    private final Atividade atividade;
    private float Nota = NOTA_INICIAL;

    // Construtor com id
    public Tarefa(int id, Aluno aluno, Turma turma, Atividade atividade) {
        this.ID = id;
        this.aluno = aluno;
        this.turma = turma;
        this.atividade = atividade;

        // Adiciona a tarefa ao HashMap
        Tarefas.put(id, this);
    }
    // Construtor com id e nota
    public Tarefa(int id, Aluno aluno, Turma turma, Atividade atividade, float nota) {
        this.ID = id;
        this.aluno = aluno;
        this.turma = turma;
        this.atividade = atividade;
        this.Nota = nota;

        // Adiciona a tarefa ao HashMap
        Tarefas.put(id, this);
    }

    // Construtor sem id
    public Tarefa(Aluno aluno, Turma turma, Atividade atividade) {
        int id = Tarefas.size() + 1; // Gera um novo ID baseado no tamanho atual do HashMap
        this.ID = id;
        this.aluno = aluno;
        this.turma = turma;
        this.atividade = atividade;

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
            if (tarefa.aluno.equals(aluno)) {
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
            if (tarefa.aluno.equals(aluno)) {
                // Verifica se a data da tarefa está dentro do período especificado
                // (não antes do início e não depois do fim)
                if (Utils.dataNoPeriodo(tarefa.atividade.getInicio(), tarefa.atividade.getFim(), inicio, fim)) {
                    // Se for, adiciona à lista de tarefas
                    tarefas.add(tarefa);
                }
            }
        }

        // Retorna o que encontrar
        return tarefas;
    }

    /**
     * Obtém todos os IDs das tarefas existentes.
     *
     * @return Um array com todos os IDs das tarefas.
     */
    public static int[] obterTodosIdsTarefas() {
        // Cria um array com todos os IDs das tarefas
        int[] ids = new int[Tarefas.size()+1];
        int i = 0;

        // Adiciona o ID proibido no início do array
        ids[i++] = ID_PROIBIDO;

        // Percorre todas as tarefas e adiciona os IDs ao array
        for (Tarefa tarefa : Tarefas.values()) {
            ids[i++] = tarefa.ID;
        }

        return ids;
    }

    /**
     * Cria uma tarefa para um aluno em uma turma com uma atividade específica.
     *
     * @param aluno O aluno que receberá a tarefa.
     * @param turma A turma onde a tarefa será criada.
     * @param atividade A atividade associada à tarefa.
     * @throws TarefaJaCriadaException Se já existir uma tarefa para o aluno e a atividade.
     */
    public static void criarTarefa(Aluno aluno, Turma turma, Atividade atividade) throws TarefaJaCriadaException {
        // Verifica se já existe uma tarefa para o aluno e a atividade
        for (Tarefa tarefa : Tarefas.values()) {
            if (tarefa.aluno.equals(aluno) && tarefa.atividade.equals(atividade)) {
                throw new TarefaJaCriadaException(atividade.getId(), aluno.getNome());
            }
        }

        // Cria a tarefa
        Tarefa tarefa = new Tarefa(aluno, turma, atividade);
    }

    /**
     * Cria uma tarefa para um aluno em uma turma com uma atividade específica.
     * Este método solicita ao usuário os dados necessários para criar a tarefa.
     *
     * @return A tarefa criada ou null se o aluno ou a turma não forem encontrados.
     */
    public static Tarefa criarTarefa() {
        // Obtem os atributos //
        // Obtém o aluno
        String matriculaAluno = Entrada.lerString("Digite a Matricula do aluno", Aluno.obterTodasMatriculas());
        Aluno aluno;
        try {
            aluno = Aluno.obterAlunoPorMatricula(matriculaAluno);
        } catch (AlunoNaoEncontradoException e) {
            // Isso nunca vai acontecer pois a matrícula é validada
            System.out.println(e.getMessage());
            return null; // Retorna null se o aluno não for encontrado
        }

        // Obtém a turma
        int idTurma = Entrada.lerInteiro("Digite o id da turma", Turma.obterTodosIdsTurmas());
        Turma turma;
        try {
            turma = Turma.obtemTurmaPorId(idTurma);
        } catch (TurmaNaoEncontradaException e) {
            // Isso nunca vai acontecer pois o id é validado
            System.out.println(e.getMessage());
            return null; // Retorna null se a turma não for encontrada
        }

        // Verifica se existem atividades na turma
        if (turma.obtemIdAtividadesDaTurma(true).length == 0) {
            System.out.println("Nao existem atividades na turma " + turma.getNome() + ". Nao e possivel criar uma tarefa.");
            return null; // Retorna null se não houver atividades na turma
        }

        // Obtém a atividade
        int idAtividade = Entrada.lerInteiro("Digite o id da atividade", turma.obtemIdAtividadesDaTurma(true));
        Atividade atividade;
        try {
            atividade = Atividade.obtemAtividadePorId(idAtividade);
        } catch (AtividadeNaoEncontradaException e) {
            // Isso nunca vai acontecer pois o id é validado
            System.out.println(e.getMessage());
            return null; // Retorna null se a atividade não for encontrada
        }

        // Pergunta se o usuário quer criar a tarefa ditando o ID
        int id;

        String resposta = Entrada.lerString("Deseja definir o ID da tarefa? (S/N)", new String[]{"S", "N"}).toUpperCase();
        if (resposta.equals("S")) {
            // Se sim, pede o ID
            int[] ids = Tarefa.obterTodosIdsTarefas();
            id = Entrada.lerInteiroExceto("Digite o ID da tarefa", ids);
        } else {
            // Se não, define o ID automaticamente
            id = 0;

            // Incrementa o ID até encontrar um que não esteja em uso
            do {
                id++;
            } while (Tarefas.containsKey(id)); // Verifica se o ID já está em uso
        }

        return new Tarefa(aluno, turma, atividade);
    }

    /**
     * Apaga todas as tarefas associadas a uma atividade.
     *
     * @param atividade A atividade a ser desassociada.
     */
    public static void desassociarAtividade(Atividade atividade) {
        // Percorre todas as tarefas e verifica se a atividade é a mesma
        for (Tarefa tarefa : Tarefas.values()) {
            // Se a atividade for a mesma, remove a tarefa
            if (tarefa.atividade.equals(atividade)) {
                Tarefas.remove(tarefa.ID);
            }
        }
    }

    /**
     * Obtém as informacoes da tarefa.
     *
     * @return Uma string com as informações da tarefa.
     */
    public String ObterInformacoes() {
        return    "|| ------ Informacoes da Atividade ------"
                + "\n|| ID: " + this.atividade.getId()
                + "\n|| Nome: " + this.atividade.getNome()
                + "\n|| Descricao: " + this.atividade.getDescricao()
                + "|| ------ Informacoes da Tarefa ------ "
                + "\n|| ID: " + this.ID
                + "\n|| Turma: " + this.turma.getNome()
                + "\n|| Aluno:\n\t" + this.aluno.getNome()
                + "\n|| Nota: " + this.Nota;
    }

    /**
     * Apaga uma tarefa da lista de tarefas.
     *
     * @param tarefa A tarefa a ser apagada.
     * @throws TarefaNaoEncontradaException Se a tarefa não for encontrada.
     */
    public static void apagarTarefaDaLista(Tarefa tarefa) throws TarefaNaoEncontradaException {
        // Verifica se a tarefa existe no HashMap
        if (Tarefas.containsKey(tarefa.ID)) {
            // Remove a tarefa do HashMap
            Tarefas.remove(tarefa.ID);
        } else {
            throw new TarefaNaoEncontradaException(tarefa.ID);
        }
    }

    /**
     * Apaga todas as tarefas associadas a uma atividade.
     *
     * @param atividade A atividade cujas tarefas serão apagadas.
     */
    public static void apagarTarefasDaAtividade(Atividade atividade) {
        // Percorre todas as tarefas e verifica se a atividade é a mesma
        for (Tarefa tarefa : Tarefas.values()) {
            // Se a atividade for a mesma, remove a tarefa
            if (tarefa.atividade.equals(atividade)) {
                try {
                    apagarTarefaDaLista(tarefa);
                } catch (TarefaNaoEncontradaException e) {
                    // Isso nunca vai acontecer
                }
            }
        }
    }

    public static void atualizarNotasDasTarefasDaAtividade(Atividade atividade, float novaNota) {
        // Percorre todas as tarefas e verifica se a atividade é a mesma
        for (Tarefa tarefa : Tarefas.values()) {
            // Se a atividade for a mesma, atualiza a nota
            if (tarefa.atividade.equals(atividade)) {
                tarefa.atualizarNota(novaNota);
            }
        }
    }

    public String toString() {
        return "\n|| Nome da Atividade: " + this.atividade.getNome()
                + "\n|| ID da Atividade: " + this.atividade.getId()
                + "\n|| Turma: " + this.turma.getNome()
                + "\n|| Nota: " + this.Nota;
    }
    // Atualizar a nota da tarefa
    public void atualizarNota(float novaNota) {this.Nota = novaNota;}
    // Obter a nota da tarefa
    public float obterNota() {return this.Nota;}
    // Getters //

    public int getID() {return this.ID;}

    public Aluno getAluno() {return this.aluno;}

    public Atividade getAtividade() {return this.atividade;}

    public float getNota() {return this.Nota;}

    public Turma getTurma() {return this.turma;}

    public static LinkedList<Tarefa> getTarefas() { return new LinkedList<Tarefa>(Tarefas.values());}

    // Setters //
    public void setNota(float nota) {this.Nota = nota;}
}
