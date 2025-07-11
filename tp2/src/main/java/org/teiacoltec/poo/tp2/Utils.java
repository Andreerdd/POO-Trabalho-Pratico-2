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

    /**
     * Formata uma string no formato dd/mm/aaaa em
     * um objeto da classe Date
     * 
     * @param entrada A string que será convertida
     * @return A data que a string foi convertida 
     */
    public static Date formatarData(String entrada) throws ParseException {
        return dataFormato.parse(entrada);
    }

    /**
     * Verifica se a data está dentro do período, incluindo o início e o fim.
     *
     * @param data A data a ser verificada
     * @param inicio A data de início do período
     * @param fim A data de fim do período
     * @return Se a data está dentro do período
     */
    public static boolean dataNoPeriodo(Date data, Date inicio, Date fim) {
        // Verifica se a data está no período
        return (!data.before(inicio) && !data.after(fim));
    }

    /**
     * Verifica se a data(inicio e fim) está dentro do período.
     *
     * @param inicioData O inicio da data a ser verificada
     * @param fimData O fim da data a ser verificada
     * @param inicio A data de início do período
     * @param fim A data de fim do período
     * @return Se a data está dentro do período
     */
    public static boolean dataNoPeriodo(Date inicioData, Date fimData, Date inicio, Date fim) {
        // Verifica se a data está no período
        return (!inicioData.before(inicio) && !fimData.after(fim));
    }

    /**
     * Embeleza o texto recebido.
     * 
     * @param textoFeio texto a ser embelezado (muito feio)
     * @return texto com formatação (bonito demais)
     */
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
}
