package cci.projetointegrador.configs;

import org.springframework.stereotype.Component;

@Component


public class ValidaRg {
    public static boolean validarRG(String rg) {
        // Remove possíveis caracteres especiais do RG (pontuação, espaços, etc.)
        rg = rg.replaceAll("[^0-9]", "");

        // Verifica se o RG tem 9 dígitos
        if (rg.length() != 9) {
            return false;
        }

        // Verifica se todos os dígitos do RG são iguais (ex: 111111111)
        if (rg.matches("(\\d)\\1+")) {
            return false;
        }

        // Calcula o dígito verificador do RG
        int soma = 0;
        int peso = 2;
        for (int i = rg.length() - 2; i >= 0; i--) {
            soma += Character.getNumericValue(rg.charAt(i)) * peso;
            peso++;
        }

        int digitoVerificador = 11 - (soma % 11);

        // Verifica se o dígito verificador é válido
        if (digitoVerificador >= 10) {
            digitoVerificador = 0;
        }

        int ultimoDigito = Character.getNumericValue(rg.charAt(rg.length() - 1));
        return ultimoDigito == digitoVerificador;
    }

    public static void main(String[] args) {
        String rg = "123456789"; // Substitua pelo RG a ser validado

        if (validarRG(rg)) {
            System.out.println("RG válido!");
        } else {
            System.out.println("RG inválido!");
        }
    }
}
