/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author agostinho
 */
public class NumeroUtil {

    /**
     * Este método formata um número para o padrão brasileiro
     *
     * @author Agostinho Neto
     * @since 10/06/2010
     * @param numero
     * @return String "R$ 1.000,00"
     */
    public static String formatarNumero_BR(String numero) {

        if(numero == null){
            numero = "0";
        }

        Locale locBR = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locBR);

        return  nf.format(Double.parseDouble(numero));
    }

    /**
     * Este método converte um número para Double,
     * se o número contiver "." ele simplismente converte,
     * se tiver vírgula ele troca a "," por "." e converte,
     * se o tamanho do número for igual a "1" o método transforma
     * este para Double e divide por 100d,
     * se for um número não formatado ele lança uma exception.
     *
     * @author Agostinho Neto
     * @since 10/06/2010
     * @param numero
     * @return Double
     */
    public static Double parseDouble(String numero) {

        Double parseNumero = null;       

        if (numero.contains(".")) {
            parseNumero = Double.parseDouble(numero);
        } else if (numero.contains(",")) {
            numero = numero.replaceAll(",", ".");
            parseNumero = Double.parseDouble(numero);
        } else if (!numero.contains(",") || !numero.contains(".")) {
            Double numerof = Double.parseDouble(numero);
            parseNumero = numerof / 100d;
        } else {
            throw new NumberFormatException();
        }

        return parseNumero;
    }
}
