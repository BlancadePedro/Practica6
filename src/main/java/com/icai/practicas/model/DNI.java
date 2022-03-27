package com.icai.practicas.model;

import java.util.Arrays;
import java.util.regex.Pattern;

//Clase tipo record
public record DNI(String dniValue) {

  private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
  private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
  private static final String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};

  //Método de validación -> Se verifican 3 condiciones
  public boolean validar() {
    return Arrays.binarySearch(INVALIDOS, dniValue) < 0 // (1) Si se encuentra en inválidos recibimos un false
            && REGEXP.matcher(dniValue).matches()       // (2) true <-> los siete primeros dígitos son números y el último una letra
            && dniValue.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dniValue.substring(0, 8)) % 23); // (3) Carácter final = letra -> true
  }
}