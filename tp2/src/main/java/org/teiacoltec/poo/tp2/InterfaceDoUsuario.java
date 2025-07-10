/*
 * Classe que será responsável pela interação principal
 * com o usuário (pelo o Menu Principal).
 */

package org.teiacoltec.poo.tp2;

import java.util.InputMismatchException;

// Imports
import org.teiacoltec.poo.tp2.Utils;
import org.teiacoltec.poo.tp2.Sistema;
import org.teiacoltec.poo.tp2.Pessoas.Pessoa;
import org.teiacoltec.poo.tp2.Escola.*;

public class InterfaceDoUsuario {

    // Valor mínimo e máximo da entrada
    private static final int ENTRADA_MIN = 0;
    private static final int ENTRADA_MAX = 4;

    /**
     * Loop que mostrará o menu e irá interagir com
     * o usuário.
     */
    public static final void MenuPrincipal() {
        int opcao;
        do {
            imprimirLinha(); // decoração
            opcao = esperarEntrada();

            // Ação da opção
            switch (opcao) {
                case 1:
                
                    Pessoa pessoa = Pessoa.criarPessoa();
                    System.out.println("Pessoa criada com as informacoes:\n");
                    imprimirInformacoes(pessoa);

                    imprimirLinha();
                    Entrada.esperaEnter();
                    
                    break;

                case 0:
                    System.out.println("Saiu do programa.");
                case Entrada.ENTRADA_INVALIDA:
                    // Não precisa de printar que digitou errado,
                    // a função já printa isso!
                    break;
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
     * Imprime as informações de uma turma.
     * 
     * @param turma turma que as informações serão impressas.
     */
    public static void imprimirInformacoes(Turma turma) {
        String informacoes = turma.ObterInformacoes();

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
    public static final void imprimirLinha() {
        System.out.println("==================================================");
    }

    /**
     * Espera e retorna a entrada do usuário.
     * 
     * @return opção escolhida (e válida) pelo usuário
     */
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
            opcaoEscolhida = Entrada.lerInteiro();

            // Verifica se a opção escolhida é válida
            if (opcaoEscolhida < ENTRADA_MIN || opcaoEscolhida > ENTRADA_MAX) {
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
