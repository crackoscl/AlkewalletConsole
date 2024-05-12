package com.alkewallet.billetera.tests;

import org.junit.Test;
import org.mockito.Mockito;

import com.alkewallet.billetera.Usuario;

import java.util.Scanner;

public class UsuarioTest {

    @Test
    public void testCrearUsuario() {
       
        Scanner scannerMock = Mockito.mock(Scanner.class);
        Mockito.when(scannerMock.next()).thenReturn("Juan","Perez","lol@lol.cl", "15893721-2");
        Usuario usuario = new Usuario();
        usuario.crearUsuario(scannerMock);
    }
}