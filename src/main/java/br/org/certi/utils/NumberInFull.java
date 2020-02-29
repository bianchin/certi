package br.org.certi.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by gian on 28/02/20.
 */
public class NumberInFull {

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

    public static String convert(final int number) {
        validate(number);

        if (number==0) {
            return ZERO;
        }

        String extenso = "";
        String[] conjuntos = separarCentenas(Math.abs(number));

        for (int i = 0; i < conjuntos.length; i++) {
            String separacao = extenso.trim().isEmpty() ? "" : " e ";
            extenso = formatCentena(Integer.valueOf(conjuntos[conjuntos.length - i - 1])) + MILHAR[i] + separacao + extenso;
        }

        if (number<0) {
            extenso = NEGATIVE + " " + extenso;
        }

        return extenso;
    }

    private static void validate(int number) {
        if (number > 99999 || number < -99999) {
            throw new RuntimeException("O número deve ser entre -99.999 e 99.999");
        }
    }

    private static String[] separarCentenas(int number) {
        return NumberFormat.getInstance(new Locale("pt", "BR")).format(number).split("\\.");
    }


    private static String formatCentena(int number) {
        String conjunto = String.valueOf(number);
        if(Math.log10(Integer.valueOf(conjunto)) % 1 == 0) {
            return LOG_10[(int)conjunto.chars().filter(ch -> ch == '0').count()];
        }

        String extenso = "";

        Integer digito = 0;
        for (int i = 0; i < conjunto.length(); i++) {
            int nChar = conjunto.length() - i;

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
