/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author agostinho
 */
public class DataUtil {

    private static Calendar dataCorrente = null;

    /**
     * Este método retorna uma String com a data atual 
     * no formato yyMMdd
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @return String = yyMMdd     
     */
    public static String getDataAtual_Short() {

        DataUtil.dataCorrente = new GregorianCalendar();
        Date date = DataUtil.dataCorrente.getTime();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyMMdd");

        return dataFormatada.format(date);
    }

    /**
     * Este método retorna uma String com a data atual 
     * formatada com "/"
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @return String = dd/MM/yyyy     
     */
    public static String getDataAtual_Long() {

        DataUtil.dataCorrente = new GregorianCalendar();
        Date date = DataUtil.dataCorrente.getTime();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

        return dataFormatada.format(date);
    }

    /**
     * Este método retorna uma String com a data atual invertida
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @return String = yyyyMMdd     
     */
    public static String getDataAtual_Long_Invertida() {

        DataUtil.dataCorrente = new GregorianCalendar();
        Date date = DataUtil.dataCorrente.getTime();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyyMMdd");

        return dataFormatada.format(date);
    }

    /**
     * Este método retorna uma String com o dia anterior 
     * a data passada como parametro.
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param Date dataInformada
     * @return String = MMyyyy     
     */
    public static String getDataAnterior(Date dataInformada) {

        Calendar dataNova = new GregorianCalendar();
        dataNova.setTime(dataInformada);
        dataNova.add(Calendar.MONTH, -1);

        Date date = dataNova.getTime();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("MMyyyy");
        return dataFormatada.format(date);
    }

    /**
     * Este método retorna uma String com a data informada
     * no formato yyMMdd
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param Date dataInformada
     * @return String = yyMMdd     
     */
    public static String getDataFormatada_Short(Date dataInformada) {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyMMdd");

        return dataFormatada.format(dataInformada);
    }

    /**
     * Este método retorna uma String com a data informada
     * no formato dd/MM/yyyy
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param Date dataInformada
     * @return String = dd/MM/yyyy     
     */
    public static String getDataFormatada_Long(Object dataInformada) {

        String dataSaida = "";

        if (dataInformada != null) {
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            dataSaida = dataFormatada.format(dataInformada);
        }

        return dataSaida;
    }

    /**
     * Este método retorna uma String com a data informada
     * no formato yyyy-MM-dd
     *
     * @author Agostinho Neto
     * @since 05/08/2009
     * @param Date dataInformada
     * @return String = yyyy-MM-dd
     */
    public static String getDataFormatada_Long_Banco(Date dataInformada) {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd");

        return dataFormatada.format(dataInformada);
    }

    /**
     * Este método retorna uma String com a data informada
     * no formato yyyyMM
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param Date dataInformada
     * @return String = dd/MM/yyyy     
     */
    public static String retornoAnoMes(Date dataInformada) {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyyMM");

        return dataFormatada.format(dataInformada);
    }

    /**
     * Este método retorna a hora da data informada
     * no formato yyMMdd
     * 
     * @author Agostinho Neto
     * @since 20/07/2009
     * @param Date dataInformada
     * @return String = HHmmss     
     */
    public static String getHora(Date dataInformada) {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("HHmmss");

        return dataFormatada.format(dataInformada);
    }

    /**
     * Este método compara duas datas retornando
     * true no caso das mesmas serem iguais e false
     * caso contrário.
     * @author Agostinho Neto
     * @since 07/06/2010
     * @param Date data1, Date data2
     * @return Boolean
     */
    public Boolean compareDatas(Date data1, Date date2) {
        return data1.equals(date2);
    }

    /**
     * Este método converte uma String em Date
     * @author Agostinho Neto
     * @since 07/06/2010
     * @param String dataOriginal
     * @return Date
     */
    public static String converteData_ddMMyyy(String dataOriginal) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(sdf.parse(dataOriginal));
    }

    /**
     * Este método converte uma String em Date
     * @author Agostinho Neto
     * @since 07/06/2010
     * @param String dataOriginal
     * @return Date
     */
    public static String converteData_yyyyMMdd(String dataOriginal) throws ParseException {

        SimpleDateFormat sdf = null;
        Date dataFormatada = null;
        String dataConvertida = "";

        if (dataOriginal != null) {

            sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataFormatada = sdf.parse(dataOriginal);
            sdf.applyPattern("dd/MM/yyyy");
            dataConvertida = sdf.format(dataFormatada);
        }

        return dataConvertida;
    }

    public static List<String> ultimosSeisMeses() {
        Calendar data = new GregorianCalendar();
        data.setTime(new Date());
        SimpleDateFormat dataFormatada = new SimpleDateFormat("MMMM");
        List<String> meses = new ArrayList<String>();

        for (int i = 0; i <= 5; i++) {
            data.add(Calendar.MONTH, -1);
            Date date = data.getTime();
            meses.add(dataFormatada.format(date));
        }
        return meses;
    }
}
