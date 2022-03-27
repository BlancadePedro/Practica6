package com.icai.practicas.model;

import org.junit.Assert;
//import com.icai.practicas.model.*;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.*;


public class DNITest {
    @Test
    public void testDNI(){

        DNI dni_valido = new DNI("52060398J");
        DNI dni_erroneo = new DNI("123456789");
        DNI invalido1 = new DNI("00000000T");
        DNI invalido2 = new DNI("00000001R");
        DNI invalido3 = new DNI("99999999R");

        //Comprobamos si se complen las especificaciones
        Assert.assertEquals(true, dni_valido.validar());
        Assert.assertEquals(false, dni_erroneo.validar());
        Assert.assertEquals(false, invalido1.validar());
        Assert.assertEquals(false, invalido2.validar());
        Assert.assertEquals(false, invalido3.validar());
    
    }
    
}
