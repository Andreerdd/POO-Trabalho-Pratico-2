package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date; // Classe Date
import java.util.HashMap; // Classe HashMap (para armazenar alunos)

// Imports
import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Escola.Tarefa;
import org.teiacoltec.poo.tp2.Excecoes.AlunoNaoEncontradoException;

public class Aluno extends Pessoa {

    // Todos os alunos existentes
    private static final HashMap<String, Aluno> Alunos = new HashMap<>();

    private String Matricula;
    private String Curso;
    private final HashMap<Integer, Tarefa> Tarefas = new HashMap<>(); // Tarefas do aluno

    public Aluno(String cpf, String nome, Date nascimento, String email, String endereco, String matricula, String curso) {
        super(cpf, nome, nascimento, email, endereco);
        this.Matricula = matricula;
        this.Curso = curso;

        Alunos.put(matricula, this);
    }

    // Obtém as informações do aluno
    @Override
    public String ObterInformacoes() {
        return super.ObterInformacoes() 
            + "\n|| Matricula: " + this.Matricula 
            + "\n|| Curso: " + this.Curso;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        // Adiciona a tarefa ao HashMap de tarefas do aluno
        Tarefas.put(tarefa.getID(), tarefa);
    }


    /**
     * Cria um novo aluno
     * 
     * @param nascimento,email,cpf,nome,endereco informações do aluno
     * @return o aluno criado 
     */
    public static Aluno criarAluno(String cpf, String nome, Date nascimento, String email, String endereco) {
        Aluno novo;

        // Obtém as informações & cria um novo aluno
        String matricula = Entrada.lerString("Matricula do aluno");
        String curso = Entrada.lerString("Curso do aluno");

        novo = new Aluno(cpf, nome, nascimento, email, endereco, matricula, curso);

        // Retorna o aluno criado
        return novo;
    }


    public static Aluno obterAlunoPorMatricula(String matricula) throws AlunoNaoEncontradoException {
        // Verifica se o aluno existe
        if (Alunos.containsKey(matricula)) {
            return Alunos.get(matricula);
        }

        // Se chegou até aqui, é porque o aluno não foi encontrado
        throw new AlunoNaoEncontradoException(matricula);
    }

    // Sets
    public void setMatricula(String matricula) {
        this.Matricula = matricula;
    }

    public void setCurso(String curso) {
        this.Curso = curso;
    }

    // Gets
    public String getMatricula() {
        return this.Matricula;
    }

    public String getCurso() {
        return this.Curso;
    }
    
}
