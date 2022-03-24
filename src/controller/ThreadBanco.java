package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	private int id;
	private double saldo;
	private double valor;
	public int tipo;
	private static int tipoSaque = 0;
	private static int tipoDeposito;
	private Semaphore semaforo = new Semaphore(2);

	public ThreadBanco(int id, double saldo, double valor, int tipo) {
		this.id = id;
		this.saldo = saldo;
		this.tipo = tipo;
		this.valor = valor;
	}

	@Override
	public void run() {
		Random numero = new Random();
		try {
			sleep((numero.nextInt(999) + 1));
			sleep((numero.nextInt(999) + 1));
			sleep((numero.nextInt(999) + 1));
			sleep((numero.nextInt(999) + 1));
			if (tipo == 1 & tipoSaque == 0) {
				semaforo.acquire();
				tipoSaque = 1;
				OperaçãoSac();
				tipoSaque = 0;
				semaforo.release();
			} else if (tipo == 1 & tipoSaque == 1) {
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				if (tipo == 1 & tipoSaque == 0) {
					semaforo.acquire();
					tipoSaque = 1;
					OperaçãoSac();
					tipoSaque = 0;
					semaforo.release();
				} else {
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					semaforo.acquire();
					tipoSaque = 1;
					OperaçãoSac();
					tipoSaque = 0;
					semaforo.release();
				}
			} else if (tipo == 2 & tipoDeposito == 0) {
				semaforo.acquire();
				tipoDeposito = 1;
				OperaçãoDep();
				tipoDeposito = 0;
				semaforo.release();
			} else if (tipo == 2 & tipoDeposito == 1) {
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				sleep((numero.nextInt(999) + 1));
				if (tipo == 2 & tipoDeposito == 0) {
					semaforo.acquire();
					tipoDeposito = 1;
					OperaçãoDep();
					tipoDeposito = 0;
					semaforo.release();
				} else {
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					sleep((numero.nextInt(999) + 1));
					semaforo.acquire();
					tipoDeposito = 1;
					OperaçãoDep();
					tipoDeposito = 0;
					semaforo.release();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void OperaçãoSac() {
		System.out.println("A conta #" + id + " tinha R$" + saldo);
		saldo -= valor;
		System.out.println("Valor sacado foi de R$" + valor);
		System.out.println("Valor restante na conta #" + id + " é de R$" + saldo);
	}

	public void OperaçãoDep() {
		System.out.println("A conta #" + id + " tinha R$" + saldo);
		saldo += valor;
		System.out.println("Valor depositado foi de R$" + valor);
		System.out.println("Valor na conta #" + id + " agora é de R$" + saldo);
	}
}
