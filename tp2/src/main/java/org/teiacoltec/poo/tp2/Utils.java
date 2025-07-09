/*
 * Classe que contém funções que podem ser úteis
 * durante o código.
 */
package org.teiacoltec.poo.tp2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    // Estilo da formatação para transformar string em data
    private static final SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy"); 

    /*
     * Formata uma string no formato dd/mm/aaaa em
     * um objeto da classe Date
     * 
     * @param entrada A string que será convertida
     * @return A data que a string foi convertida 
     */
    public static final Date formatarData(String entrada) throws ParseException {
        return dataFormato.parse(entrada);
    }

    /*
     * Embeleza o texto recebido.
     * 
     * @param textoFeio texto a ser embelezado (muito feio)
     * @return texto com formatação (bonito demais)
     */
    public static final String embelezar(String textoFeio) {
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
}
