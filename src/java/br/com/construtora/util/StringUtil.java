/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

/**
 *
 * @author agostinho
 */
public class StringUtil {

    /**
     * Este método retorna uma String retirando alguns caracteres,
     * como por exemplo: '-', ' ', '(', ')', '/', ',', '.', etc, retornando
     * uma String limpa.
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param String conta
     * @return StringBuilder
     */
    public static String retiraCaracteres(String original) {
        String nova = original.replaceAll("-", "").replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\/", "").replaceAll("\\\\", "").replaceAll(" ", "").replaceAll("  ", "").replaceAll(",", "").replaceAll("\\.", "").replaceAll("<", "").replaceAll(">", "").replaceAll("=", "").replaceAll("%", "").replaceAll("R", "").replace("$", "");
        return nova;
    }

    /**
     * Este método retorna uma String com a palavra passada como parametro sem acentos
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param String palavraAcentuada
     * @return String
     */
    public static String retiraAcentos(String palavraAcentuada) {

        String palavraTemporaria = palavraAcentuada;

        String palavraFormatada = palavraTemporaria.replaceAll("à", "a").replaceAll("À", "A").replaceAll("á", "a").replaceAll("Á", "A").replaceAll("ã", "a").replaceAll("Ã", "A").replaceAll("é", "e").replaceAll("É", "E").replaceAll("ê", "e").replaceAll("Ê", "E").replaceAll("í", "i").replaceAll("Í", "I").replaceAll("ó", "o").replaceAll("Ó", "O").replaceAll("ô", "o").replaceAll("Ô", "O").replaceAll("õ", "o").replaceAll("Õ", "O").replaceAll("ú", "u").replaceAll("Ú", "U").replaceAll("ç", "c").replaceAll("Ç", "C");
        return palavraFormatada;

    }

    /**
     * Este método retorna uma StringBuilder com a conta formatada,
     * se o tamanho da conta for menor do que 6 o método completa com "0".
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param String conta
     * @return StringBuilder
     */
    public static StringBuilder formataConta(String conta) {

        StringBuilder contaFormatada = new StringBuilder(conta);
        String contaTmp = "";

        if (!conta.contains("-")) {
            if (contaFormatada.length() < 6) {

                for (int i = 0; i < (6 - contaFormatada.length()); i++) {
                    contaTmp += "0";
                }
            }

            contaFormatada.insert(0, contaTmp);
            contaFormatada.insert(contaFormatada.length() - 1, "-");
        }

        return contaFormatada;
    }

    /**
     * Este método retorna uma StringBuilder com a palavra completada
     * com o caracter passado com parâmetro.
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param String palavra
     * @param String caracterComplemento
     * @param Integer tamanho
     * @return StringBuilder
     */
    public static StringBuilder completaPalavra(String palavra, String caracterComplemento, Integer tamanho, Character localizacao) {

        StringBuilder palavraFormatada = new StringBuilder(palavra);
        String complemento = "";

        if (palavraFormatada.length() < tamanho) {
            for (int i = 0; i < (tamanho - palavraFormatada.length()); i++) {
                complemento += caracterComplemento;
            }
            if (localizacao == 'I') {
                palavraFormatada.insert(0, complemento);
            } else if (localizacao == 'F') {
                palavraFormatada.insert(palavraFormatada.length(), complemento);
            }
        } else if (palavraFormatada.length() > tamanho) {
            palavraFormatada.delete(0, palavraFormatada.length() - tamanho);
        }

        return palavraFormatada;
    }

    /**
     * Este método retorna uma String com o cpf formatado
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param Integer cpf
     * @return StringBuilder
     */
    public static StringBuilder formataCPFCNPJ(String cpfCnpj) {

        StringBuilder contaFormatada = null;

        if (cpfCnpj.length() == 11) {
            contaFormatada = new StringBuilder(cpfCnpj);
            contaFormatada.insert(3, ".").insert(7, ".").insert(11, "-");
        } else if (cpfCnpj.length() == 14) {
            contaFormatada = new StringBuilder(cpfCnpj);
            contaFormatada.insert(2, ".").insert(6, ".").insert(10, "/").insert(15, "-");
        }

        return contaFormatada;
    }

    /**
     * Este método retorna uma StringBuilder com o caracter passado com parametro
     * replicado quantas vezes for solicitado
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param String caracterReplicado
     * @param Integer quantidade
     * @return StringBuilder
     */
    public static StringBuilder replicate(String caracterReplicado, Integer quantidade) {

        StringBuilder stringFormatada = new StringBuilder();

        for (int i = 0; i < quantidade; i++) {
            stringFormatada.insert(0, caracterReplicado);
        }

        return stringFormatada;
    }

    /**
     * Este método retorna uma String com o valor do campo de um arquivo,
     * passando o delimitador e a coluna que se deseja retornar
     *
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param String linha
     * @param String delimitador
     * @param Integer indexColuna
     * @return String
     */
    public static String retornoColuna(String linha, String delimitador, Integer indexColuna) {

        String[] retorno = null;
        String coluna = "";

        for (int i = 0; i < linha.length(); i++) {
            retorno = linha.split(delimitador);
        }

        if (indexColuna < retorno.length) {
            coluna = retorno[indexColuna];
        } else {
            coluna = "Coluna inexistente!";
        }

        return coluna;
    }

    /**
     * Este método retorna uma String com o cep formatado
     *
     * @author Agostinho Neto
     * @since 02/02/2011
     * @param String cepNaoFormatado
     * @return String
     */
    public static String formataCEP(String cepNaoFormatado) {

        StringBuilder sb = new StringBuilder(cepNaoFormatado);
        sb.insert(2, ".");
        sb.insert(6, "-");

        return sb.toString();
    }

    /**
     * Este método retorna uma String com o telefone formatado
     *
     * @author Agostinho Neto
     * @since 14/05/2011
     * @param String telefoneNaoFormatado
     * @return String
     */
    public static String formataTelefone(String telefoneNaoFormatado) {

        StringBuilder sb = new StringBuilder(telefoneNaoFormatado);
        sb.insert(4, '-');

        return sb.toString();
    }
}
