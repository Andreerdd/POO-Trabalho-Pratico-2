/**
 * Classe que representa uma pessoa no sistema.
 */

package org.teiacoltec.poo.tp2.Pessoas;

import java.util.Date; // Classe Date
import java.util.HashMap;
import java.util.LinkedList;

// Imports
import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Excecoes.PessoaForaDaListaException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaNaoEncontradaException;
import org.teiacoltec.poo.tp2.Utils;

public abstract class Pessoa {

    // o "0" pode ser usado para sair de alguns menus
    private static final String MATRICULA_PROIBIDA = "0";
    private static final String CPF_PROIBIDO = "0";

    // Todas as pessoas existentes
    private static final HashMap<String, Pessoa> Pessoas = new HashMap<>();

    // Para o código ficar mais bonito
    private static final String[] tiposDePessoas = new String[]{"Aluno", "Professor", "Monitor"};
    
    private final String CPF;
    private String Nome;
    private Date Nascimento; 
    private String Email;
    private String Endereco;
    
    
    public Pessoa(String cpf, String nome, Date nascimento, String email, String endereco) {
        this.CPF = cpf;
        this.Nome = nome;
        this.Nascimento = nascimento;
        this.Email = email;
        this.Endereco = endereco;
        
        Pessoas.put(cpf, this);
    }

    /**
     * Obtém as informações da pessoa.
     *
     * @return informações da pessoa formatadas
     */
    private String ObterInformacoesBase() {
        return  "|| Nome: " + this.Nome 
            + "\n|| CPF: " + this.CPF 
            + "\n|| Data de Nascimento: " + Utils.data_ddMMaaaa(this.getNascimento())
            + "\n|| Email: " + this.Email 
            + "\n|| Endereco: " + this.Endereco;
    }

    /**
     * Obtém as informações da pessoa.
     *
     * @return informações da pessoa formatadas
     */
    public String ObterInformacoes() {
        return ObterInformacoesBase();
    }

    /**
     * Obtém as informações da pessoa, incluindo o tipo de pessoa.
     *
     * @param tipo tipo da pessoa
     * @return informações da pessoa formatadas
     */
    public String ObterInformacoes(String tipo) {
        return  "|| Tipo: " + tipo + "\n" + ObterInformacoesBase();
    }

    /**
     * Obtém todos os CPFs das pessoas cadastradas.
     *
     * @return um array de strings contendo todos os CPFs
     */
    public static String[] obterTodosCpfs() {
        String[] cpfs = new String[Pessoas.size() + 1];

        // Transforma os valores do HashMap em um array
        Pessoa[] pessoasArray = Pessoas.values().toArray(new Pessoa[0]);

        // Adiciona o CPF proibido no início do array
        cpfs[0] = CPF_PROIBIDO;

        for (int i = 1; i <= Pessoas.size(); i++) {
            cpfs[i] = pessoasArray[i - 1].getCPF();
        }

        return cpfs;
    }

    /**
     * Obtém todas as matrículas das pessoas cadastradas.
     *
     * @return um array de strings contendo todas as matrículas
     */
    public static String[] obterTodasMatriculas() {
        String[] matriculas = new String[Pessoas.size() + 1];

        // Transforma os valores do HashMap em um array
        Pessoa[] pessoasArray = Pessoas.values().toArray(new Pessoa[0]);

        // Adiciona a matrícula proibida no início do array
        matriculas[0] = MATRICULA_PROIBIDA;

        for (int i = 1; i <= Pessoas.size(); i++) {
            // Não gosto de instanceof mas, por motivos de filtragem,
            // é necessário aqui :(

            // Verifica se é uma pessoa matriculada
            if (pessoasArray[i - 1] instanceof Matriculado matriculado) {
                matriculas[i] = matriculado.getMatricula();
            }
        }


        return matriculas;
    }

    /**
     * Obtém todas as matrículas das pessoas cadastradas.
     *
     * @param excecao a matrícula que deve ser ignorada
     * @return um array de strings contendo todas as matrículas
     */
    public static String[] obterTodasMatriculas(String excecao) {
        LinkedList<String> matriculasList = new LinkedList<>();
        matriculasList.add(MATRICULA_PROIBIDA); // Adiciona a matrícula proibida

        for (Pessoa pessoa : Pessoas.values()) {
            // Não gosto de usar instanceof, mas é necessário aqui para filtrar

            // Não precisa desse if pois, nesse caso, todos são matriculados.
            if (pessoa instanceof Matriculado matriculado) {
                String matricula = matriculado.getMatricula();

                // Verifica se a matrícula é diferente da exceção
                if (!matricula.equals(excecao)) {
                    matriculasList.add(matricula);
                }
            }
        }

        // Converte a lista para um array
        return matriculasList.toArray(new String[0]);
    }


    /**
     * Cria uma nova pessoa, pedindo as informações necessárias ao usuário.
     *
     * @return a nova pessoa criada
     */
    public static Pessoa criarPessoa() {
        Pessoa novo = null;

        // Obtem todos os CPFs
        String[] cpfs = obterTodosCpfs();

        // Obtém todas as matrículas
        String [] matriculas = obterTodasMatriculas();

        // Pede as informações comuns a todas as pessoas
        String nome = Entrada.lerString("Digite o nome da pessoa");
        String cpf = Entrada.lerStringExceto("Digite o CPF da pessoa", cpfs);
        Date dataNascimento = Entrada.lerData("Digite a data de nascimento da pessoa");
        String email = Entrada.lerString("Digite o email da pessoa");
        String endereco = Entrada.lerString("Digite o endereco da pessoa");

        do {
            String tipoDePessoa = Entrada.lerString("Digite o tipo de pessoa (Aluno, Professor ou Monitor)", tiposDePessoas);

            // Deixa tudo minúsculo para facilitar a comparação
            tipoDePessoa = tipoDePessoa.toLowerCase();

            // Verifica se o tipo de pessoa é válido
            switch (tipoDePessoa) {
                case "aluno" -> novo = Aluno.criarAluno(cpf, nome, dataNascimento, email, endereco, matriculas);
                case "professor" -> novo = Professor.criarProfessor(cpf, nome, dataNascimento, email, endereco, matriculas);
                case "monitor" -> novo = Monitor.criarMonitor(cpf, nome, dataNascimento, email, endereco, matriculas);
                default -> System.out.println("Tipo de pessoa invalido. Isso nao deveria acontecer!");
            }
        } while (novo == null); // Enquanto não for criado uma pessoa válida

        return novo;
    }

    /**
     * Obtém uma pessoa a partir do seu CPF
     * 
     * @param cpf o cpf da pessoa a ser procurada
     * @return a pessoa encontrada
     * @throws PessoaNaoEncontradaException se a pessoa não for encontrada
     */
    public static Pessoa obtemPessoaPorCPF(String cpf) throws PessoaNaoEncontradaException {
        // Verifica se a pessoa existe
        if (Pessoas.containsKey(cpf)) return Pessoas.get(cpf);

        // Se chegou até aqui, é porque a pessoa não foi encontrada
        throw new PessoaNaoEncontradaException("Nao encontrou a pessoa com cpf: " + cpf);
    }

    /**
     * Apaga uma pessoa da lista de pessoas. Note que
     * apaga APENAS DA LISTA DE PESSOAS!
     * 
     * @param pessoa a pessoa que se quer apagar
     * @throws PessoaForaDaListaException se a pessoa não estiver na lista
     */
    public static void apagarPessoaDaLista(Pessoa pessoa) throws PessoaForaDaListaException {
        // Verifica se a pessoa não está na lista
        if (!Pessoas.containsKey(pessoa.getCPF())) throw new PessoaForaDaListaException(pessoa.getNome());
        
        // Se chegou até aqui, a pessoa está na lista
        Pessoas.remove(pessoa.getCPF());
    }

    // O método equals (igualdade) verifica se duas pessoas são iguais
    public boolean equals(Pessoa pessoa) {
        // Verifica se tem o mesmo CPF
        return pessoa.getCPF().equals(this.getCPF());
    }

    // Setters //

    // O CPF só fica livre se a pessoa morrer (for removida da lista)
    // public void setCPF(String cpf) { this.CPF = cpf; }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public void setNascimento(Date nascimento) {
        this.Nascimento = nascimento;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setEndereco(String endereco) {
        this.Endereco = endereco;
    }

    // Getters //
    public String getNome() {
        return this.Nome;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getEndereco() {
        return this.Endereco;
    }

    public Date getNascimento() {
        return this.Nascimento;
    }

    public String getCPF() { return this.CPF; }
    
    public static HashMap<String, Pessoa> getPessoas() {
        return Pessoas; 
    }
    
    
}
