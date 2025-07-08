/*
 * Classe que contém funções que podem ser úteis
 * durante o código.
 */
package org.teiacoltec.poo.tp2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    // Formatação para transformar string em data
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
}
