package br.org.certi.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * Classe ultilitária para converter um numérico em número em extenso
 *
 * Created by gian on 28/02/20.
 *
 */
public class NumeroExtensoUtils {

    private static final String[][] MATRIX = {
        { "",  "um",   "dois",  "três", "quatro",   "cinco",   "seis",
                "sete", "oito", "nove" },
            {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},
            {"", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"}
        };


    private static final String[] DEZENA = {"dez",  "onze", "doze", "treze",
            "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};

    private static final String[] LOG_10 = {"um", "dez", "cem", "mil", "dez mil"};

    private static final String[] MILHAR = {  "", " mil"};

    private static final String ZERO = "zero";

    private static final String NEGATIVE = "menos";

    public static String converter(final int number) {
        validar(number);

        // Caso for zero, já retorna sem passar pelo algorítimo
        if (number==0) {
            return ZERO;
        }

        String extenso = "";
        String[] conjuntos = separarCentenas(Math.abs(number));

        //Pega os blocos de centena e transforma em extenso. Dentro de cada bloco é a mesma regra, só colocando mil ou nada
        for (int i = 0; i < conjuntos.length; i++) {
            String separacao = extenso.trim().isEmpty() ? "" : " e ";
            extenso = formataCentena(Integer.valueOf(conjuntos[conjuntos.length - i - 1])) + MILHAR[i] + separacao + extenso;
        }

        //Veritica se é negativo para colocar o menos na frente
        if (number<0) {
            extenso = NEGATIVE + " " + extenso;
        }

        return extenso;
    }

    private static void validar(int number) {
        if (number > 99999 || number < -99999) {
            throw new RuntimeException("O número deve ser entre -99.999 e 99.999");
        }
    }

    /**
     * Separa o numeral em blocos de centenas
     *
     * @param number
     * @return
     */
    private static String[] separarCentenas(int number) {
        return NumberFormat.getInstance(new Locale("pt", "BR")).format(number).split("\\.");
    }


    /**
     * Formata uma sentena em extenso
     *
     * @param number
     * @return
     */
    private static String formataCentena(int number) {
        String conjunto = String.valueOf(number);
        if(Math.log10(Integer.valueOf(conjunto)) % 1 == 0) {
            return LOG_10[(int)conjunto.chars().filter(ch -> ch == '0').count()];
        }

        String extenso = "";

        Integer digito = 0;
        for (int i = 0; i < conjunto.length(); i++) {
            int nChar = conjunto.length() - i;

            //caso for entre 11 e 19, tem uma unica excessão quanto das demais regras
            if (i==1 && conjunto.substring(nChar-1 ,nChar).equals("1")) {
                extenso = DEZENA[digito];
                continue;
            }

            digito = Integer.parseInt(conjunto.substring(nChar-1 ,nChar));

            String nome = MATRIX[i][digito];
            String separacao = nome.trim().isEmpty() || extenso.trim().isEmpty() ? "" : " e ";
            extenso =  nome + separacao + extenso;
        }
        return extenso;

    }




}
