package com.alkewallet.billetera.utils;

public class LimpiarPantalla {

    public static void limpiarConsola() {

		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				new ProcessBuilder("clear").inheritIO().start().waitFor();
			}
		} catch (Exception e) {
			System.out.println("Error al intentar limpiar la consola: " + e.getMessage());
		}
	}

}
