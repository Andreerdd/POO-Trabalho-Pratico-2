package org.teiacoltec.poo.tp2;

// Classe Date (antiga)
import java.util.Date; 

// Classe Scanner para entradas
import java.util.Scanner; 
import java.util.InputMismatchException;

// Imports
import org.teiacoltec.poo.tp2.Escola.*;
import org.teiacoltec.poo.tp2.Excecoes.*; 
import org.teiacoltec.poo.tp2.Pessoas.*;



public class Main {

    // Configurações
    private static final boolean RODAR_TESTE_COMUM = false;

    // Constantes
    private static final int ENTRADA_INVALIDA = -1;
    private static final int ENTRADA_SAIR = 0;

    public static final Scanner inScanner = new Scanner(System.in);

    // Espera o usuário dar enter para continuar
    public static void esperaEnter() {
        inScanner.nextLine(); // Limpa o buffer
        System.out.println("Pressione Enter para continuar...");
        inScanner.nextLine();
    }

    // Embeleza o texto (não julgue)
    public static String embelezar(String textoFeio) {
        String textoBonito = "";

        for (int i = 0; i < textoFeio.length(); i++) {
            // textoFeio.charAt(i) é equivalente a textoFeio[i] (em C)
            char caractere = textoFeio.charAt(i);
            textoBonito += caractere;

            // Verifica se não está no final da string
            if (i < textoFeio.length() - 3) {

                // Verifica se é uma quebra de linha (que não está com ||)
                if (caractere == '\n' && (textoFeio.charAt(i+1) != '|' || textoFeio.charAt(i+2) != '|')) { 
                    // Adiciona a quebra de linha e um "|| " depois
                    textoBonito += "||";
                }
            }
        }

        return textoBonito;
    }

    // Imprime as informações de uma pessoa
    public static void imprimirInformacoes(Pessoa pessoa) {
        String informacoes = pessoa.ObterInformacoes();

        System.out.println("\n================================================================\n" 
                           + embelezar(informacoes) 
                         + "\n================================================================");
    }

    // Imprime as informações de uma turma
    public static void imprimirInformacoes(Turma turma) {
        String informacoes = turma.ObterInformacoes();

        System.out.println("\n================================================================\n" 
                           + embelezar(informacoes) 
                         + "\n================================================================");
    }

    // Imprime as informações de uma atividade
    public static void imprimirInformacoes(Atividade atividade) {
        String informacoes = atividade.ObterInformacoes();

        System.out.println("\n================================================================\n" 
                           + embelezar(informacoes) 
                         + "\n================================================================");
    }

    // Método que mostra algumas das funcionalidades do programa
    private static void testeComum(){
        // Cria alguns alunos
        Pessoa aluno1 = new Aluno(
            "123456789-01", 
            "Joao Paulo", 
            new Date(109, 4, 14), // O ano começa contando a partir de 1900, por isso é 109 o valor do ano (1900+109=2009)
            "joaopaulo132@gmail.com", 
            "Rua legal, 123", 
            "2023456123", 
            "Eletronica"
            );
        Pessoa aluno2 = new Aluno(
            "123456789-02", 
            "Carlos Santos", 
            new Date(108, 3, 13),
            "csantos2008@gmail.com", 
            "Rua Antonio Carolina, 4561", 
            "2023456167", 
            "Eletronica"
            );

        Pessoa aluno3 = new Aluno(
            "321654788-01", 
            "Sabrina Carpenter", 
            new Date(99, 4, 13),
            "carpenter1999@gmail.com", 
            "Rua Nao Sei, 6969", 
            "2024661204", 
            "Informatica"
            );


        // Printa informações dos alunos
        System.out.println("\n\nInformacoes dos alunos:\n");
        imprimirInformacoes(aluno1);
        imprimirInformacoes(aluno2);
        imprimirInformacoes(aluno3);

        // Cria um professor
        Pessoa professor1 = new Professor(
            "132547698-08", 
            "Francisco de Assis", 
            new Date(80, 8, 8),
            "franciscaoassis08@gmail.com", 
            "Rua Carlos Lucas, 789", 
            "2019102030", 
            "Eletronica"
        );

        Pessoa professor2 = new Professor(
            "132583928-08", 
            "Eduarda Pereira", 
            new Date(86, 12, 9),
            "dudapereira@yahoo.com", 
            "Rua Copacabana, 1823", 
            "2016443234", 
            "Informatica"
        );

        System.out.println("\n\nInformacoes dos professores:\n");
        imprimirInformacoes(professor1);
        imprimirInformacoes(professor2);

        // Cria turmas e subturmas
        Turma turma10 = new Turma(
            10, 
            "Turma 10", 
            "Turma 10 demais!! Eletronica acima de tudo!!", 
            new Date(124, 1, 1), 
            new Date(124, 11, 31)
            );

        Turma turma10_1 = new Turma(
            1001, 
            "Turma 10-1", 
            "Turma 10-1, so os melhores aqui", 
            new Date(124, 1, 1), 
            new Date(124, 11, 31)
        );

        Turma turma10_1_1 = new Turma(
            100101, 
            "Turma 10-1-1", 
            "Turma 10-1-1, so pra mostrar que da pra ter sub-subturmas", 
            new Date(120, 1, 1), 
            new Date(140, 11, 31)
        );

        Turma turma20 = new Turma(
                20, 
                "Turma 20", 
                "Turma 20 de Informatica :D", 
                new Date(124, 6, 1), 
                new Date(124, 12, 31)
            );


        // Configura a turma 10
        try {
            // Adiciona alunos e um professor na turma 10
            turma10.adicionarParticipante(aluno1);
            turma10.adicionarParticipante(aluno2);
            turma10.adicionarParticipante(professor1);

            // Associa a turma 10-1 à turma 10 & adiciona um aluno e um professor
            turma10.associaSubturma(turma10_1);
            turma10_1.adicionarParticipante(aluno2);
            turma10_1.adicionarParticipante(professor1);

            // Associa a turma 10-1-1 à turma 10-1 & adiciona um aluno
            turma10_1.associaSubturma(turma10_1_1);
            turma10_1_1.adicionarParticipante(aluno1);
        } catch (PessoaJaParticipanteException | TurmaJaEstaAssociadaException e) {
            System.out.println(e.getMessage());

        }

        // Configura a turma 20
        try {
            // Adiciona alunos na turma 20 e um professor
            turma20.adicionarParticipante(aluno1);
            turma20.adicionarParticipante(aluno2);
            turma20.adicionarParticipante(professor2);
        } catch (PessoaJaParticipanteException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\nInformacoes das turmas:\n");

        // Mostra informações da turma 10
        imprimirInformacoes(turma10);

        // Mostra informações da turma 10-1
        imprimirInformacoes(turma10_1);

        // Mostra informações da turma 10-1-1
        imprimirInformacoes(turma10_1_1);

        // Mostra informações da turma 20
        imprimirInformacoes(turma20);

        // Cria atividades
        Atividade atividade1 = new Atividade(
            11,
            "Atividade 1 de Informatica",
            "Calculadora",
            new Date(124, 7, 15),
            new Date(124, 8, 15),
            5f
        );
        
        Atividade atividade2 = new Atividade(
            12,
            "Trabalho Final",
            "Mercado Desobstruido",
            new Date(124, 9, 1),
            new Date(124, 10, 30),
            100f
        );
        
        Atividade atividade3 = new Atividade(
            21,
            "Atividade de Eletronica",
            "LEDs",
            new Date(124, 3, 5),
            new Date(124, 5, 13),
            10f
        );

        // Printa informações das atividades
        System.out.println("\n\nInformacoes das atividades:\n");
        imprimirInformacoes(atividade1);
        imprimirInformacoes(atividade2);
        imprimirInformacoes(atividade3);

        // Remove o aluno 1 da turma 10
        System.out.println("\n\nRemovendo aluno 1 da turma 10...\n");
        try {
            turma10.removerParticipante(aluno1);
        } catch (PessoaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }

        // Tenta remover o aluno 3 da turma 10
        System.out.println("\n\nRemovendo aluno 3 da turma 10...\n");
        try {
            turma10.removerParticipante(aluno3);
        } catch (PessoaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }

        // Printa informações da turma 10
        imprimirInformacoes(turma10);

        // Altera a descrição da turma 10
        System.out.println("\n\nAlterando a descrição da turma 10...\n");
        turma10.setDescricao("Turma 10 demais!! Eletronica acima de tudo!!\n\tAgora com menos alunos! Ou seja, mais espaco para voce!!");

        // Printa informações da turma 10
        imprimirInformacoes(turma10);
    }

    // Valor mínimo e máximo da entrada
    private static final int ENTRADA_MIN = 0;
    private static final int ENTRADA_MAX = 4;

    // Espera e retorna a entrada do usuário
    private static int esperarEntrada() {
        int opcaoEscolhida;

        // Printa as opções
        System.out.println("""
            Opcoes:
            0 - Sair

            == Pessoas ==
            1 - Criar pessoa

            """);

        try {
            opcaoEscolhida = inScanner.nextInt();
            if (opcaoEscolhida < ENTRADA_MIN || opcaoEscolhida > ENTRADA_MAX) {
                throw new InputMismatchException("Digite uma opcao valida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: " + e.getMessage());
            esperaEnter();
            opcaoEscolhida = ENTRADA_INVALIDA;
        }

        return opcaoEscolhida;
    }
    
    public static void main(String[] args) {

        // Roda um teste para mostrar algumas funcionalidades do programa
        // caso o usuário queira.
        if (RODAR_TESTE_COMUM) testeComum();

        // A variável vai ser sobrescrita de qualquer forma
        int opcao;

        do {
            opcao = esperarEntrada();

            // Ação da opção
            switch (opcao) {
                case 1:
                    
                    break;

                case 0:
                    System.out.println("Saiu do programa.");
                case ENTRADA_INVALIDA:
                    // Não precisa de printar que digitou errado,
                    // a função já printa isso!
                    break;
            }
        } while (opcao != ENTRADA_SAIR);

    }
}
