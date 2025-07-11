package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date; // Classe Date
import java.util.HashMap; // Classe HashMap (para armazenar alunos)
import java.util.LinkedList;

// Imports
import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Escola.Tarefa;
import org.teiacoltec.poo.tp2.Excecoes.AlunoNaoEncontradoException;

public class Aluno extends Pessoa implements Matriculado {

    private String Matricula;
    private String Curso;

    public Aluno(String cpf, String nome, Date nascimento, String email, String endereco, String matricula, String curso) {
        super(cpf, nome, nascimento, email, endereco);
        this.Matricula = matricula;
        this.Curso = curso;
    }

    // Obtém as informações do aluno
    @Override
    public String ObterInformacoes() {
        String informacoesTarefas = "\n|| Tarefas: ";
        LinkedList<Tarefa> Tarefas = new LinkedList<>();
        Tarefas.addAll(Tarefa.obterTarefasDaPessoa(this));
        
        // Verifica se o aluno tem tarefas
        if (Tarefas.isEmpty()) {
            informacoesTarefas = "Nenhuma tarefa cadastrada.";
        } else {
            informacoesTarefas += Tarefas.toString();
        }
        return super.ObterInformacoes("Aluno")
            + "\n|| Matricula: " + this.Matricula 
            + "\n|| Curso: " + this.Curso;
    }

    /**
     * Obtém todos os alunos cadastrados.
     *
     * @return HashMap com a chave sendo a matrícula e o valor sendo o respectivo aluno.
     */
    public static HashMap<String, Aluno> obterAlunos() {
        HashMap<String, Aluno> alunos = new HashMap<>();

        // Obtém os alunos do HashMap de pessoas
        for (Pessoa pessoa : getPessoas().values()) {
            /* Pessoalmente, não gosto de fazer instanceof, mas, como
            aqui o meu objetivo é filtrar e armazenar outro hashmap
            com alunos poderia ser confuso e poderia gerar erros, abri
            essa exceção. */
            // Verifica se a pessoa é um aluno
            if (pessoa instanceof Aluno temp) {
                alunos.put(temp.getMatricula(), temp);
            }
        }

        return alunos;
    }

    /**
     * Cria um novo aluno
     * 
     * @param nascimento,email,cpf,nome,endereco informações do aluno
     * @return o aluno criado 
     */
    public static Aluno criarAluno(String cpf, String nome, Date nascimento, String email, String endereco, String[] matriculas) {
        Aluno novo;

        // Obtém as informações & cria um novo aluno
        String matricula = Entrada.lerStringExceto("Matricula do aluno", matriculas);
        String curso = Entrada.lerString("Curso do aluno");

        novo = new Aluno(cpf, nome, nascimento, email, endereco, matricula, curso);

        // Retorna o aluno criado
        return novo;
    }   

    /**
     * Obtém um aluno pelo número de matrícula.
     *
     * @param matricula A matrícula do aluno a ser buscado.
     * @return O aluno correspondente à matrícula fornecida.
     * @throws AlunoNaoEncontradoException Se o aluno não for encontrado.
     */
    public static Aluno obterAlunoPorMatricula(String matricula) throws AlunoNaoEncontradoException {
        // Obtém os alunos
        HashMap<String, Aluno> Alunos = obterAlunos();

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
