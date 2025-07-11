/*
 * Classe que será responsável pela interação principal
 * com o usuário (pelo o Menu Principal).
 */

package org.teiacoltec.poo.tp2;

import java.util.InputMismatchException;

import org.teiacoltec.poo.tp2.Escola.Atividade;
import org.teiacoltec.poo.tp2.Escola.Tarefa;
import org.teiacoltec.poo.tp2.Escola.Turma;
import org.teiacoltec.poo.tp2.Pessoas.Pessoa;

public class InterfaceDoUsuario {

    // Valor mínimo e máximo da entrada
    private static final int ENTRADA_MIN = 0;
    private static final int ENTRADA_MAX = 16;

    /**
     * Loop que mostrará o menu e irá interagir com
     * o usuário.
     */
    public static void MenuPrincipal() {
        int opcao;
        do {
            // decoração
            System.out.println();
            imprimirLinha(); 

            // Printa as opções
            System.out.println("""
                                 Opcoes:                        \s
            0 - Sair                                            \s
                                                                \s
            ===== Pessoas =====            ===== Turmas =====   \s
            1 - Criar pessoa               5 - Criar Turma      \s
            2 - Remover Pessoa             6 - Remover Turma    \s
            3 - Atualizar Pessoa           7 - Atualizar Turma  \s
            4 - Listar Pessoas             8 - Listar Turmas    \s
                                                                \s
            ===== Atividades =====         ===== Tarefas =====  \s
            9 - Criar Atividade            15 - Criar Tarefa    \s
            10 - Remover Atividade         16 - Remover Tarefa  \s
            11 - Atualizar Atividade       17 - Atualizar Tarefa\s
            12 - Listar Atividades         18 - Listar Tarefas  \s
            13 - Associar Atividade                             \s
            14 - Desassociar Atividade                          \s
                                                                \s""");

            opcao = esperarEntrada(ENTRADA_MIN, ENTRADA_MAX);

            // Ação da opção
            switch (opcao) {
                case 1 -> FuncoesMenu.criarPessoa();
                case 2 -> FuncoesMenu.removerPessoa();
                case 3 -> FuncoesMenu.atualizarPessoa();
                case 4 -> FuncoesMenu.listarPessoas();

                case 5 -> FuncoesMenu.criarTurma();
                case 6 -> FuncoesMenu.removerTurma();
                case 7 -> FuncoesMenu.atualizarTurma();
                case 8 -> FuncoesMenu.listarTurmas();

                case 9 -> FuncoesMenu.criarAtividade();
                case 10 -> FuncoesMenu.removerAtividade();
                case 11 -> FuncoesMenu.atualizarAtividade();
                case 12 -> FuncoesMenu.listarAtividades();
                case 13 -> FuncoesMenu.associarAtividadeATurma();
                case 14 -> FuncoesMenu.desassociarAtividadeDaTurma();

                case 15 -> FuncoesMenu.criarTarefa();
                case 16 -> FuncoesMenu.removerTarefa();
                case 17 -> FuncoesMenu.atualizarTarefa();
                case 18 -> FuncoesMenu.listarTarefas();

                case 0 -> System.out.println("Saiu do programa.");
                
                // Não precisa contar com isso
                // case Entrada.ENTRADA_INVALIDA
            }
        } while (opcao != Entrada.ENTRADA_SAIR);
    }

    /**
     * Imprime as informações de uma pessoa.
     * 
     * @param pessoa pessoa que as informações serão impressas.
     */
    public static void imprimirInformacoes(Pessoa pessoa) {
        String informacoes = pessoa.ObterInformacoes();

        imprimirLinha();
        System.out.println(Utils.embelezar(informacoes));
        imprimirLinha();
    }

    /**
     * Imprime as informações de uma tarefa.
     *
     * @param tarefa tarefa que as informações serão impressas.
     */
    public static void imprimirInformacoes(Tarefa tarefa) {
        String informacoes = tarefa.ObterInformacoes();

        imprimirLinha();
        System.out.println(Utils.embelezar(informacoes));
        imprimirLinha();
    }


    /**
     * Imprime as informações de uma turma.
     * 
     * @param turma turma que as informações serão impressas.
     */
    public static void imprimirInformacoes(Turma turma) {
        String informacoes = turma.ObterInformacoes(true);

        imprimirLinha();
        System.out.println(Utils.embelezar(informacoes));
        imprimirLinha();
    }

    /**
     * Imprime as informações de uma atividade.
     * 
     * @param atividade atividade que as informações serão impressas.
     */
    public static void imprimirInformacoes(Atividade atividade) {
        String informacoes = atividade.ObterInformacoes();

        imprimirLinha();
        System.out.println(Utils.embelezar(informacoes));
        imprimirLinha();
    }


    /**
     * Imprime uma linha de igual.
     */
    public static void imprimirLinha() {
        System.out.println("==================================================");
    }

    /**
     * Espera e retorna a entrada do usuário.
     * 
     * @return opção escolhida (e válida) pelo usuário
     */
    private static int esperarEntrada(int min, int max) {
        int opcaoEscolhida;

        try {
            opcaoEscolhida = Entrada.lerInteiro("Opcao");

            // Verifica se a opção escolhida é válida
            if (opcaoEscolhida < min || opcaoEscolhida > max) {
                throw new InputMismatchException("Digite uma opcao valida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler a entrada: " + e.getMessage());
            Entrada.esperaEnter();
            opcaoEscolhida = Entrada.ENTRADA_INVALIDA;
        }

        return opcaoEscolhida;
    }
}
