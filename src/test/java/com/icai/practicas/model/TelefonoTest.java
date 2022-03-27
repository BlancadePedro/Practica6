package com.icai.practicas.model;

import org.junit.Assert;
//import com.icai.practicas.model.*;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.*;


public class TelefonoTest {
    @Test
    public void testTelefono(){

        Telefono tel1 = new Telefono("646513445");
        Telefono tel2 = new Telefono("+34646513445");
        Telefono tel3 = new Telefono("914484126");
        Telefono tel4 = new Telefono("+48914484126");
        Telefono tel5 = new Telefono("12345678A");
        Telefono tel6 = new Telefono("112233445566778899");


        //Comprobamos si se complen las especificaciones
        Assert.assertEquals(true, tel1.validar());
        Assert.assertEquals(true, tel2.validar());
        Assert.assertEquals(true, tel3.validar());
        Assert.assertEquals(true, tel4.validar());
        Assert.assertEquals(false, tel5.validar());
        Assert.assertEquals(false, tel6.validar());
    
    }
}
