package view;

import java.util.Random;

import controller.ThreadBanco;

public class Principal {

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			Random numero = new Random();
			int tipo = (numero.nextInt(1) + 1);
			int id = (numero.nextInt(8999) + 1000);
			double saldo = (numero.nextDouble() * (numero.nextInt(10000) + 4000));
			double valor = (numero.nextDouble() * (numero.nextInt(4000) + 1));
			Thread t = new ThreadBanco(id, saldo, valor, tipo);
			t.start();
		}
	}
}
