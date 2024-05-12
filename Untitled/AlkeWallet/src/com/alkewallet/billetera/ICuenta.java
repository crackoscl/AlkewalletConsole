package com.alkewallet.billetera;

public interface ICuenta {
		public void retirar(Double cantidad);
		public double consultaSaldo();
		public void depositar(Double cantidad);
}
