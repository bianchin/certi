package br.org.certi.dto;

import br.org.certi.utils.NumeroExtensoUtils;

public class Numero {

	private String extenso;

    public Numero() {

    }

    public String getExtenso() {
        return extenso;
    }

    public static final class NumeroBuilder {
        private Integer numero;

        private NumeroBuilder() {
        }

        public static NumeroBuilder aNumero() {
            return new NumeroBuilder();
        }

        public NumeroBuilder withNumero(Integer numero) {
            this.numero = numero;
            return this;
        }

        public Numero build() {
            Numero numero = new Numero();
            numero.extenso = NumeroExtensoUtils.converter(this.numero);
            return numero;
        }
    }
}
