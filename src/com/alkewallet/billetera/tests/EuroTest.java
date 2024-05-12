package com.alkewallet.billetera.tests;

import org.junit.Test;

import com.alkewallet.billetera.Euro;

import static org.junit.Assert.*;

public class EuroTest {

    @Test
    public void testGetNombre() {
        Euro euro = new Euro("Euro", "€", "Euros", 1.0);
        assertEquals("Euro", euro.getNombre());
    }

    @Test
    public void testGetValor() {
        Euro euro = new Euro("Euro", "€", "Euros", 1.0);
        assertEquals(1.0, euro.getValor(), 0.001);
    }

    @Test
    public void testGetPluralName() {
        Euro euro = new Euro("Euro", "€", "Euros", 1.0);
        assertEquals("Euros", euro.getPluralName());
    }

    @Test
    public void testGetSimbol() {
        Euro euro = new Euro("Euro", "€", "Euros", 1.0);
        assertEquals("€", euro.getSimbol());
    }

    @Test
    public void testSetNombre() {
        Euro euro = new Euro();
        euro.setNombre("Euro");
        assertEquals("Euro", euro.getNombre());
    }

    @Test
    public void testSetValor() {
        Euro euro = new Euro();
        euro.setValor(1.0);
        assertEquals(1.0, euro.getValor(), 0.001);
    }

    @Test
    public void testSetPluralName() {
        Euro euro = new Euro();
        euro.setPlural_name("Euros");
        assertEquals("Euros", euro.getPluralName());
    }

    @Test
    public void testSetSimbol() {
        Euro euro = new Euro();
        euro.setSimbol("€");
        assertEquals("€", euro.getSimbol());
    }
}