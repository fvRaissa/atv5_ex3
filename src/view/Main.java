package view;

import java.util.concurrent.Semaphore;

import controller.Triatlo;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		Semaphore semaforo2 = new Semaphore(1);
		
		for(int i = 1; i<26;i++) {
			Triatlo T = new Triatlo(i,semaforo,semaforo2);
			T.start();
		}

	}

}
