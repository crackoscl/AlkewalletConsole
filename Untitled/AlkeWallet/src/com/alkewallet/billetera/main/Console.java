package com.alkewallet.billetera.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.alkewallet.billetera.Usuario;
import com.alkewallet.billetera.utils.LimpiarPantalla;

public class Console {
	Usuario usuario;
	

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		Console console = new Console();
		console = console.mainMenu(console,entrada);
		entrada.close();
		System.out.println("Cerrando Aplicación....");
	}

	private Console mainMenu(Console console,Scanner entrada) {
		LimpiarPantalla.limpiarConsola();
		int selection = 0;
		do {
			System.out.println("Billetera Virtual");
			System.out.println("[1] Crear Usuario");
			System.out.println("[2] Ver saldo en la cuenta");
			System.out.println("[3] Depositar o retirar dinero");
			System.out.println("[4] Convertir Moneda");
			System.out.println("[5] Salir");
			System.out.print("Selecione una opción: ");

			try {
				selection = entrada.nextInt();
			} catch (InputMismatchException e) {
				entrada.next().charAt(0);
			}

			switch (selection) {
				case 1:
					this.usuario = new Usuario();
					usuario.crearUsuario(entrada);
					usuario.getCuenta().setUsuario(usuario);
					break;
				case 2:
					if (this.usuario != null) {
						System.out.println("Su saldo es : $" + " " + usuario.getCuenta().consultaSaldo());
					} else {
						System.out.println("Aun no ha creado un usuario");
					}
					break;
				case 3:
					return console.submenu1(console, entrada);
				case 4:
					if (this.usuario != null) {
					usuario.getCuenta().ConvertirMoneda(entrada);
					}else{
						System.out.println("Aun no ha creado un usuario");
					}
					break;
				case 5:
					return console;
				default:
					System.out.println("Opcion selecionada invalida!");
			}
			System.out.println("Teclea \"c\" y luegos preciona la tecla [enter] para continuar");
			entrada.next().charAt(0);
			LimpiarPantalla.limpiarConsola();
		} while (selection != 5);
		return console;
	}

	private Console submenu1(Console console, Scanner entrada) {
		LimpiarPantalla.limpiarConsola();
		int selection = 0;

		do {
			System.out.println("Welcome to the SUBMENU");
			System.out.println("[1] Depositar");
			System.out.println("[2] Retirar");
			System.out.println("[5] Return");
			System.out.print(" Selecione un Opcion: ");

			try {
				selection = entrada.nextInt();
			} catch (InputMismatchException e) {
				entrada.next().charAt(0);
			}

			switch (selection) {
				case 1:
					if (this.usuario != null) {
						System.out.print("Ingrese un monto a Depositar:");
						String input = entrada.next();
						try {
							Double monto = Double.parseDouble(input);
							usuario.getCuenta().depositar(monto);
						} catch (NumberFormatException e) {
							System.out.println("Debe ingresar un monto Valido");
						}
					} else {
						System.out.println("Aun no ha creado un usuario");
					}
					break;
				case 2:
					if (this.usuario != null) {
						Double saldo = usuario.getCuenta().consultaSaldo();
						if (saldo > 0) {
							System.out.print("Ingrese un monto a Retirar:");
							String input = entrada.next();
							try {
								Double monto = Double.parseDouble(input);
								usuario.getCuenta().retirar(monto);
							} catch (NumberFormatException e) {
								System.out.println("Debe ingresar un monto Valido");
							}
						} else {
							System.out.println("no tiene saldo Suficiente para Retirar");
						}
					} else {
						System.out.println("Aun no ha creado un usuario");
					}
					break;

				case 5:
					return console.mainMenu(console, entrada);
				default:
					System.out.println("Opcion selecionada invalida!");
			}
			System.out.println("Teclea \"c\" y luegos preciona la tecla [enter] para continuar");
			entrada.next().charAt(0);
			LimpiarPantalla.limpiarConsola();
		} while (selection != 5);
		return console;
	}
}
