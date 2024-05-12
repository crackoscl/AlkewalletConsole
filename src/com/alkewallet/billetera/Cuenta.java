package com.alkewallet.billetera;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alkewallet.billetera.api.ApiMonedas;
import com.alkewallet.billetera.utils.LimpiarPantalla;

public class Cuenta implements ICuenta {
	private String numeroCuenta;
	private double saldo = 0;
	private Usuario usuario;

	/**
     * Constructor de la clase Cuenta que genera un número de cuenta aleatorio.
     */
	public Cuenta() {
		this.numeroCuenta = "CL"
				+ new Random().ints(10, 0, 10).mapToObj(Integer::toString).collect(Collectors.joining());
	}

	/**
     * Constructor de la clase Cuenta que inicializa la cuenta con un usuario, número de cuenta y saldo.
     * @param usuario El usuario asociado a la cuenta.
     * @param numeroCuenta El número de cuenta.
     * @param saldo El saldo inicial de la cuenta.
     */
	public Cuenta(Usuario usuario, double numeroCuenta, double saldo) {
		this.saldo = saldo;
		this.usuario = usuario;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	 /**
     * Realiza un depósito en la cuenta.
     * @param monto El monto a depositar.
     */
	
	@SuppressWarnings("resource")
	@Override
	public void depositar(Double monto) {
		while (!validarDeposito(monto)) {
			System.out.println("Monto inválido. Por favor, ingrese un Monto válido:");
			try {
				Double input = Double.parseDouble(new Scanner(System.in).next());
				monto = input;
			} catch (NumberFormatException e) {
			}
			LimpiarPantalla.limpiarConsola();
		}
		this.saldo += monto;
		System.out.println();
		System.out.printf("--------------------------------%n");
		System.out.printf(" Deposito realizado con exito    %n");
		System.out.printf("--------------------------------%n");
		System.out.printf(" %-10s : %-8s        %n", "Nombre", usuario.getNombre());
		System.out.printf(" %-10s : %-8s        %n", "N°Cuenta:", getNumeroCuenta());
		System.out.printf(" %-10s : %-8s        %n", "saldo:", consultaSaldo());
		System.out.printf("--------------------------------%n");
		System.out.println();
	}
	
	 /**
     * Realiza un retiro de la cuenta.
     * @param monto El monto a retirar.
     */
	@SuppressWarnings("resource")
	@Override
	public void retirar(Double monto) {
		while (!validarRetiro(monto)) {
			System.out.println("Monto inválido. Por favor, ingrese un Monto válido:");
			try {
				Double input = Double.parseDouble(new Scanner(System.in).next());
				monto = input;
			} catch (NumberFormatException e) {
			}
			LimpiarPantalla.limpiarConsola();
		}
		this.saldo -= monto;

		System.out.println();
		System.out.printf("--------------------------------%n");
		System.out.printf(" Retiro realizado con exito    %n");
		System.out.printf("--------------------------------%n");
		System.out.printf(" %-10s : %-8s        %n", "Nombre", usuario.getNombre());
		System.out.printf(" %-10s : %-8s        %n", "N°Cuenta:", getNumeroCuenta());
		System.out.printf(" %-10s : %-8s        %n", "saldo:", consultaSaldo());
		System.out.printf("--------------------------------%n");
		System.out.println();

	}

	@Override
	public double consultaSaldo() {
		return this.saldo;
	}

	 /**
     * Convierte el saldo de la cuenta a una moneda específica.
     * @param entrada Scanner para entrada de datos.
     */
	public void ConvertirMoneda(Scanner entrada) {
		if (this.saldo > 0) {
			ApiMonedas monedas = new ApiMonedas();
			monedas.GetIndicadores();
			System.out.print("Ingrese tipo de cambio dolar - euro: ");
			String tipoCambio = entrada.next();
			Double monto = null;
			entrada.nextLine();
			try {
				System.out.print("Ingrese Monto a convertir: ");
				String inputmonto = entrada.next();
				if (!isNumeric(inputmonto)) {
					throw new Exception("Error: Debe ingresar un número válido");
				}

				monto = Double.parseDouble(inputmonto);
				if (monto > saldo) {
					throw new Exception("Error: monto es mayor a saldo disponible");
				} else if (monto <= 0) {
					throw new Exception("Error: el monto no es valido");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				return;
			}
			if (monedas.getMonedas().containsKey(tipoCambio)) {
				Double valorDolar = 1 / monedas.getMonedas().get(tipoCambio).getValor().doubleValue();
				Double valorFinal = monto * valorDolar;
				System.out.println("El resultado de la conversión es:"
						+ monedas.getMonedas().get(tipoCambio).getSimbol().toString() + " " + valorFinal + " "
						+ monedas.getMonedas().get(tipoCambio).getPluralName().toString());
			} else {
				System.out.println("Error: El tipo de cambio ingresado no está disponible.");
			}
		} else {
			System.out.println("No tiene saldo para convertir.");
		}
	}

	/**
     * Verifica si una cadena es numérica.
     * @param str La cadena a verificar.
     * @return true si la cadena es numérica, false de lo contrario.
     */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	 /**
     * Valida si la cantidad a depositar es válida.
     * @param cantidad La cantidad a validar.
     * @return true si la cantidad es válida, false de lo contrario.
     */
	public boolean validarDeposito(Double cantidad) {
		if (cantidad <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Valida si la cantidad a retirar es válida.
	 * @param cantidad La cantidad a validar.
	 * @return true si la cantidad es válida para el retiro, false de lo contrario.
	 */
	public boolean validarRetiro(Double cantidad) {
		if (cantidad <= 0) {
			return false;
		} else if (this.saldo < cantidad) {
			return false;
		} else {
			return true;
		}
	}
}
