package com.alkewallet.billetera.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.alkewallet.billetera.Usd;

public class UsdTest {

    @Test
    public void testGetNombre() {
        Usd dolar = new Usd("Dolar", "$", "Dolares", 1.0);
        assertEquals("Dolar", dolar.getNombre());
    }

    @Test
    public void testGetValor() {
        Usd dolar = new Usd("Dolar", "$", "Dolares", 1.0);
        assertEquals(1.0, dolar.getValor(), 0.001);
    }

    @Test
    public void testGetPluralName() {
    	Usd dolar = new Usd("Dolar", "$", "Dolares", 1.0);
        assertEquals("Dolares", dolar.getPluralName());
    }

    @Test
    public void testGetSimbol() {
    	Usd dolar = new Usd("Dolar", "$", "Dolares", 1.0);
        assertEquals("$", dolar.getSimbol());
    }

    @Test
    public void testSetNombre() {
        Usd dolar = new Usd();
        dolar.setNombre("Dolar");
        assertEquals("Dolar", dolar.getNombre());
    }

    @Test
    public void testSetValor() {
    	Usd dolar = new Usd();
        dolar.setValor(1.0);
        assertEquals(1.0, dolar.getValor(), 0.001);
    }

    @Test
    public void testSetPluralName() {
    	Usd dolar = new Usd();
        dolar.setPlural_name("Dolares");
        assertEquals("Dolares", dolar.getPluralName());
    }

    @Test
    public void testSetSimbol() {
    	Usd dolar = new Usd();
        dolar.setSimbol("$");
        assertEquals("$", dolar.getSimbol());
    }
}