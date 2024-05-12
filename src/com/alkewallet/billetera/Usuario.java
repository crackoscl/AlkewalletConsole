package com.alkewallet.billetera;
import java.util.Scanner;
import java.util.UUID;

import com.alkewallet.billetera.utils.LimpiarPantalla;

public class Usuario {
	private UUID userId;
	private String nombre;
	private String apellido;
	private String rut;
	private String email;
	private Cuenta cuenta;

	public Usuario() {
		this.userId = UUID.randomUUID();
		cuenta = new Cuenta();
	}

	public Usuario(UUID userId, String nombre,String apellido, String rut, String email, Cuenta cuenta) {
		this.userId = userId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rut = rut;
		this.email = email;
		this.cuenta = cuenta;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {
		return email;
	}

	

	public void setEmail(String email) {
		this.email = email;
	}


	public UUID getUserId() {
		return userId;
	}
	
	public void crearUsuario(Scanner entrada) {
		System.out.println();
		System.out.print("ingrese su Nombre:");
		String nombre = entrada.next();
		while (!validarString(nombre)) {
			System.out.println("Nombre inválido. Por favor, ingrese un nombre válido:");
			nombre = entrada.next();
		}
		setNombre(nombre);
		entrada.nextLine();
		
		System.out.print("ingrese su Apellido:");
		String apellido = entrada.next();
		while (!validarString(apellido)) {
			System.out.println("Nombre inválido. Por favor, ingrese un apellido válido:");
			apellido = entrada.next();
		}
		setApellido(apellido);
		entrada.nextLine();
		System.out.print("ingrese su email:");
		String email = entrada.next();
		while (!validarEmail(email)) {
			System.out.println("Email inválido. Por favor, ingrese un Email válido:");
			email = entrada.next();
		}
		setEmail(email);

		System.out.print("ingrese su Rut:");
		String rut = entrada.next();
		while (!validarRut(rut)) {
			System.out.println("Rut inválido. Por favor, ingrese un Rut válido:");
			rut = entrada.next();
		}
		setRut(rut);
		
		LimpiarPantalla.limpiarConsola();

		System.out.println();
		System.out.printf("--------------------------------%n");
		System.out.printf(" usuario Creado con exito       %n");
		System.out.printf("--------------------------------%n");
		System.out.printf(" %-10s : %-8s        %n", "Nombre", getNombre());
		System.out.printf(" %-10s : %-8s        %n", "Email", getEmail());
		System.out.printf(" %-10s : %-8s        %n", "Rut", getRut());
		System.out.printf(" %-10s : %-8s        %n", "N°Cuenta:", cuenta.getNumeroCuenta());
		System.out.printf("--------------------------------%n");
		System.out.println();
	}

	public boolean validarString(String string) {
		return !string.isEmpty() && string.matches("[a-zA-Z]+");
	}

	public boolean validarEmail(String email) {
		String emailRegex = "^[\\w-+]+(\\.[\\w-]{1,62}){0,126}@[\\w-]{1,63}(\\.[\\w-]{1,62})+[\\w-]+$";
		return email.matches(emailRegex);
	}

	public boolean validarRut(String rut) {
		boolean validacion = false;
        rut =  rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

        char dv = rut.charAt(rut.length() - 1);

        int m = 0, s = 1;
        for (; rutAux != 0; rutAux /= 10) {
            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
        }
        if (dv == (char) (s != 0 ? s + 47 : 75)) {
            validacion = true;
        }
    return validacion;
	}
}