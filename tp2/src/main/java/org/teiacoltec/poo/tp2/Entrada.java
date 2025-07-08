/*
 * Classe responsável pelo tratamento de entradas.
 * Recomendo usar aqui para evitar erros como 
 * "entradas de tipos incorretos".
 * 
 *  Author: André Dias
 *  08/07/2025
 */

package org.teiacoltec.poo.tp2;

import org.teiacoltec.poo.tp2.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        Entrada.lerString("Pressione Enter para continuar...");
    }

    /*
     * Imprime uma linha de igual.
     */
    public static final void imprimirLinha() {
        System.out.println("==================================================");
    }

    /*
     * Lê uma string do usuário.
     * 
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return A string lida do usuário.
     */
    public static final String lerString(String mensagem) {
        System.out.println(mensagem);
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
                System.out.println("Entrada invalida. Tente novamente.");
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
        System.out.println(mensagem);
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
                entrada = inScanner.nextInt();
                
                // Se chegou aqui, a entrada é válida (nenhuma exceção)
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Tente novamente.");
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
        System.out.println(mensagem);
        return lerData();
    }

    public static final Date lerData() {
        Date data;
        String entrada;


        System.out.println("Formato (dd/mm/aaaa):");

        // Loop para garantir que a entrada seja válida
        do {
            try {
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