/*
 * Classe responsável pelo tratamento de entradas.
 * Recomendo usar aqui para evitar erros como 
 * "entradas de tipos incorretos".
 * 
 *  Author: André Dias
 *  08/07/2025
 */

package org.teiacoltec.poo.tp2;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

// Imports
import org.teiacoltec.poo.tp2.Utils;
import org.teiacoltec.poo.tp2.InterfaceDoUsuario;

public class Entrada {
    // Constantes //
    public static final int ENTRADA_INVALIDA = -1; // Se a pessoa digitou algo inválido
    public static final int ENTRADA_SAIR = 0;      // Opção que o usuário deve digitar para sair de algo

    // Scanner responsável pela entrada durante todo o programa
    public static final Scanner inScanner = new Scanner(System.in);

    /*
     * Espera o usuário dar enter para continuar.
     */
    public static final void esperaEnter() {
        System.out.println("Pressione Enter para continuar...");
        
        // A função lerString() é preparada para NÃO ler uma string vazia,
        // o que acontece aqui. Por isso, usei essa linha abaixo ao invés
        // da função supracitada.
        inScanner.nextLine();
    }
    
    /*
     * Lê uma string do usuário enquanto o usuário não digitar algo
     * dentro do vetor "entradas"
     * 
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @param entradas Array com as strings válidas aceitas.
     * @return A string lida do usuário.
     */
    public static final String lerString(String mensagem, String[] entradas) {
        String entrada;
        boolean entradaValida = false;

        do {
            entrada = lerString(mensagem);
            
            // Verifica se a entrada está no array de entradas válidas
            for (String item : entradas) {
                if (entrada.equalsIgnoreCase(item)) {
                    entradaValida = true;
                    break;
                }
            }
            
            // Se chegou até aqui, é porque a entrada é inválida
            if (!entradaValida) {
                System.out.println("Entrada inválida. Digite uma das opções válidas:\n");
                for (String item : entradas) {
                    System.out.println(" - " + item + "\n");
                }
            }
        } while (!entradaValida);

        return entrada;
    }

    /*
     * Lê uma string do usuário.
     * 
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return A string lida do usuário.
     */
    public static final String lerString(String mensagem) {
        System.out.print(mensagem);
        return lerString();
    }

     /*
     * Lê uma string do usuário.
     * 
     * @return A string lida do usuário.
     */
    public static final String lerString() {
        String entrada;

        // Loop para garantir que a entrada seja válida
        do {
            try {
                System.out.print("\n: ");
                entrada = inScanner.nextLine();

                // Verifica se a entrada é válida
                if (entrada.isEmpty()) {
                    System.out.println("Entrada invalida. Tente novamente.");
                    esperaEnter();
                    continue;
                }
                
                // Se chegou aqui, a entrada é válida (nenhuma exceção && não é vazia)
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Tente novamente.\nMensagem de erro: " + e.getMessage());
            }
        } while (true);

        // Só chega aqui se a entrada for válida
        return entrada;
    }


    /*
     * Lê um inteiro do usuário.
     * 
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return O inteiro lido do usuário.
     */
    public static final int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return lerInteiro();
    }

    /*
     * Lê um inteiro do usuário.
     * 
     * @return O inteiro lido do usuário.
     */
    public static final int lerInteiro() {
        int entrada;

        // Loop para garantir que a entrada seja válida
        do {
            try {
                System.out.print("\n: ");
                String strEntrada = inScanner.nextLine();
                entrada = Integer.parseInt(strEntrada);
                // Se chegou aqui, a entrada é válida (nenhuma exceção)
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Entrada invalida. Tente novamente.\nMensagem de erro: " + e.getMessage());
                esperaEnter();
            }
        } while (true);

        // Só chega aqui se a entrada for válida
        return entrada;
    }

    /*
     * Lê uma data do usuário.
     * 
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return A data lida do usuário.
     */
    public static final Date lerData(String mensagem) {
        System.out.print(mensagem);
        return lerData();
    }

    public static final Date lerData() {
        Date data;
        String entrada;


        System.out.print("\t\t(Formato (dd/mm/aaaa) como em 13/04/1999)");

        // Loop para garantir que a entrada seja válida
        do {
            try {
                System.out.print("\n: ");
                entrada = inScanner.nextLine();

                // Verifica se a entrada é válida
                if (entrada.isEmpty()) {
                    System.out.println("Entrada invalida. Tente novamente.");
                    esperaEnter();
                    continue;
                }

                data = Utils.formatarData(entrada);
                break;
            } catch (InputMismatchException | ParseException e) {
                System.out.println("Entrada invalida. Tente novamente.\nMensagem de erro: " + e.getMessage());
            }
        } while (true);

        // Só chega aqui se a entrada for válida
        return data;
    }
}