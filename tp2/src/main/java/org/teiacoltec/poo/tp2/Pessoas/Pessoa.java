package org.teiacoltec.poo.tp2.Pessoas;

import java.text.SimpleDateFormat; // Classe SimpleDateFormat (para formatar a data)
import java.util.Date; // Classe Date

// Imports
import org.teiacoltec.poo.tp2.Entrada; 

public abstract class Pessoa {
    private String CPF;
    private String Nome;
    private Date Nascimento; 
    private String Email;
    private String Endereco;
    private static final String[] tiposDePessoas = new String[]{"Aluno", "Professor"}; // para o código ficar mais bonito
    
    public Pessoa(String cpf, String nome, Date nascimento, String email, String endereco) {
        this.CPF = cpf;
        this.Nome = nome;
        this.Nascimento = nascimento;
        this.Email = email;
        this.Endereco = endereco;
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
    

    
    
}
