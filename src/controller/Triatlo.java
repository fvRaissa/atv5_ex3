package controller;

import java.util.concurrent.Semaphore;

public class Triatlo extends Thread{

	private int atleta;
	private Semaphore semaforo;
	private Semaphore semaforo2;
	private static int vtPont [][]= new int[25][2];
	private static int pontosCo = 25;
	private static int pontosTi = 25;
	private static int pontosCi = 25;
	
	public Triatlo(int atleta, Semaphore semaforo,Semaphore semaforo2) {
		this.atleta = atleta;
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
	}
	
	@Override
	public void run() {
		
		corrida();
		try {
			semaforo2.acquire();
			try {
				semaforo.acquire();
				semaforo2.release();
				tiroAlvo();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
			ciclismo();
		}
		
		
		
	}

	private void corrida() {
		int percTotal = 3000;
		int percorrido = 0;
		while(percorrido < percTotal) {
			
			int corre = (int)((Math.random()*5.1)+20);
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			percorrido += corre;
			if(percorrido >percTotal)
				percorrido = 3000;
			System.out.println("O atleta "+atleta+" percorreu  "+percorrido+" m. na corrida");
			
		}
		//System.out.println("O atleta "+atleta+" percorreu  "+percorrido+" m.");
		vtPont [atleta-1][0] = atleta;
		vtPont [atleta-1][1] = pontosCo * 10;
		pontosCo -= 1;
		
		
	}

	
	private void tiroAlvo() {
		System.out.println(atleta+"PEGOU A ARMA");
		int tiro = 0;
		while(tiro < 3) {
			int tempoTiro = (int)((Math.random()*2.6)+0.5);
			try {
				sleep(tempoTiro);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
			System.out.println("O atleta "+atleta+" deu o "+(tiro+1)+"° tiro");
			tiro++;
		}
		
		vtPont[atleta-1][1] += pontosTi *10;
		pontosTi -= 1;
		
	}

	private void ciclismo() {
		int percTotal = 5000;
		int percorrido = 0;
		while(percorrido < percTotal) {
			int corre = (int)((Math.random()*11)+30);
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			percorrido += corre;
			System.out.println("O atleta "+atleta+" percorreu  "+percorrido+" m. no ciclismo");
			if(percorrido >percTotal)
				percorrido = 5000;
		}
		//System.out.println("O atleta "+atleta+" percorreu  "+percorrido+" m.");
		vtPont[atleta-1][1] += pontosCi *10;
		pontosCi -= 1;
		
		if(pontosCi ==0) {
			for(int i = 0; i<24;i++) {
				for(int j = i; j<25;j++) {
					if(vtPont[i][1] < vtPont[j][1]) {
						int res = vtPont[i][1];
						int at = vtPont[i][0];
						
						vtPont[i][1] = vtPont[j][1];
						vtPont[i][0] = vtPont[j][0];
						
						vtPont[j][1] = res;
						vtPont[j][0] = at;
					}
				}
			}
			
			System.out.println("POSIÇÃO | ATLETA | PONTOS");
			for(int i =0;i<25;i++) {
			
				System.out.println("  "+(i+1)+"°      "+vtPont[i][0] +"      "+vtPont[i][1]);
			}
		}
	}
}
