package com.alkewallet.billetera.tests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.alkewallet.billetera.Cuenta;
import com.alkewallet.billetera.Usuario;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CuentaTest {
	@Test
	void testDepositar() {
		Cuenta cuenta = new Cuenta();
		Usuario usuario  = new Usuario();
		usuario.setNombre("Juan");
		cuenta.setUsuario(usuario);
		
		String input = "100.0";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		try (Scanner scanner = new Scanner(in)) {
			Scanner mockScanner = Mockito.mock(Scanner.class);
			when(mockScanner.next()).thenReturn(scanner.next());
		}

		cuenta.depositar(1000.0);

		assertEquals(1000.0, cuenta.consultaSaldo());
	}
	
	@Test
	void testRetirar() {
		Cuenta cuenta = new Cuenta();
		Usuario usuario  = new Usuario();
		usuario.setNombre("Juan");
		cuenta.setUsuario(usuario);
		cuenta.setSaldo(1000.0);
		
		String input = "500.0";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		try (Scanner scanner = new Scanner(in)) {
			Scanner mockScanner = Mockito.mock(Scanner.class);
			when(mockScanner.next()).thenReturn(scanner.next());
		}

		cuenta.retirar(500.0);

		assertEquals(500.0, cuenta.consultaSaldo());
	}
	
	@Test
	void ConvertirMoneda() {
		Scanner scannerMock = Mockito.mock(Scanner.class);
        Mockito.when(scannerMock.next()).thenReturn("dolar","500");
        Usuario usuario = new Usuario();
        Cuenta cuenta = new Cuenta();
		usuario.setNombre("Juan");
		cuenta.setUsuario(usuario);
		cuenta.setSaldo(1000.0);
        cuenta.ConvertirMoneda(scannerMock);
	}
}
