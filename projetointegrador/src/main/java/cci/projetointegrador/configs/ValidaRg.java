package cci.projetointegrador.configs;

import org.springframework.stereotype.Component;

@Component


public class ValidaRg {
    public static boolean validarRG(String rg) {

        rg = rg.replaceAll("[^0-9]", "");


        if (rg.length() != 9) {
            return false;
        }


        if (rg.matches("(\\d)\\1+")) {
            return false;
        }


        int soma = 0;
        int peso = 2;
        for (int i = rg.length() - 2; i >= 0; i--) {
            soma += Character.getNumericValue(rg.charAt(i)) * peso;
            peso++;
        }

        int digitoVerificador = 11 - (soma % 11);


        if (digitoVerificador >= 10) {
            digitoVerificador = 0;
        }

        int ultimoDigito = Character.getNumericValue(rg.charAt(rg.length() - 1));
        return ultimoDigito == digitoVerificador;
    }

    public static void main(String[] args) {
        String rg = "123456789";

        if (validarRG(rg)) {
            System.out.println("RG válido!");
        } else {
            System.out.println("RG inválido!");
        }
    }
}
