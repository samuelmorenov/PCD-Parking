package Parking;
public class Pasarela extends Thread {
	String nombre;
	int estado = 0; //0 = abajo; 1 = moviendose; 2 = arriba;
	//Object O;

	public Pasarela(String nombre_
			//, Object O_
			) {
		nombre = nombre_;
		//O = O_;
	}
	synchronized public void levantar_barrera() {
		while (estado != 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		estado = 1;
		try {
			Thread.sleep (5);//Tiempo que tarda la barrera en subir
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		estado = 2;
		System.out.println("La barrera " + nombre + " se ha subido.");
		this.notifyAll();
	}
	synchronized public void bajarBarrera(){
		while (estado != 2){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		estado = 1;
		try {
			Thread.sleep (5);//Tiempo que tarda la barrera en bajar
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		estado = 0;
		System.out.println("La barrera " + nombre + " se ha bajado.");
		System.out.println();
		this.notifyAll();
		
	}
	synchronized public void pasar(int ticket){
		while(estado != 2){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Un coche " + ticket + " ha pasado por la barrera " + nombre + ".");
	}
	synchronized public boolean estado(){
		while(estado == 1){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(estado == 0){
			return false;
		}
		else{
			return true;
		}

}
}