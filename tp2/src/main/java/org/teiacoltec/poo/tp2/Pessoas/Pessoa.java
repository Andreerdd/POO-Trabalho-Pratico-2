package org.teiacoltec.poo.tp2.Pessoas;

import java.text.SimpleDateFormat; // Classe SimpleDateFormat (para formatar a data)
import java.util.Date; // Classe Date
import java.util.HashMap;

import org.teiacoltec.poo.tp2.Entrada;
import org.teiacoltec.poo.tp2.Excecoes.PessoaForaDaListaException;
import org.teiacoltec.poo.tp2.Excecoes.PessoaNaoEncontradaException; 

public abstract class Pessoa {

    // Todas as pessoas existestes
    private static final HashMap<String, Pessoa> Pessoas = new HashMap<>();

    // Para o código ficar mais bonito
    private static final String[] tiposDePessoas = new String[]{"Aluno", "Professor"};
    
    private String CPF;
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

    // Retorna as informações da pessoa de forma organizada
    public String ObterInformacoes() {
        // Formata a data de nascimento
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(this.Nascimento);
        
        return  "|| Nome: " + this.Nome 
            + "\n|| CPF: " + this.CPF 
            + "\n|| Data de Nascimento: " + dataFormatada 
            + "\n|| Email: " + this.Email 
            + "\n|| Endereco: " + this.Endereco;
    }

    public static Pessoa criarPessoa() {
        Pessoa novo = null;

        // Pede as informações comuns a todas as pessoas
        String nome = Entrada.lerString("Digite o nome da pessoa");
        String cpf = Entrada.lerString("Digite o CPF da pessoa");
        Date dataNascimento = Entrada.lerData("Digite a data de nascimento da pessoa");
        String email = Entrada.lerString("Digite o email da pessoa");
        String endereco = Entrada.lerString("Digite o endereco da pessoa");

        do {
            String tipoDePessoa = Entrada.lerString("Digite o tipo de pessoa (Aluno ou Professor)", tiposDePessoas);

            switch (tipoDePessoa) {
                case "Aluno":
                    novo = (Pessoa) Aluno.criarAluno(cpf, nome, dataNascimento, email, endereco);
                    break;
                default: System.out.println("Tipo de pessoa invalido. Isso nao deveria acontecer!");
            }
        }
        while (novo == null);

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
     * Apaga uma pessoa da lista de pessoas
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

    // Sets  
    public void setCPF(String cpf) {
        this.CPF = cpf;
    }

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

    // Gets
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

    public String getCPF() {
        return this.CPF;
    }
    
    public static HashMap<String, Pessoa> getPessoas() {
        return Pessoas; 
    }
    
    
}
