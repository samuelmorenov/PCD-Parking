package Parking;

import java.rmi.RemoteException;

import Cliente.Main;


public class Ticketero {
	int maxcoches = 5;
	Main main;
	int ticket = 0;
	Pasarela pasarela;
	Object O;
	
	public Ticketero (Main M_, Pasarela pasarela_, Object O_) {
		main = M_;
		pasarela = pasarela_;
		O = O_;
	}
	
	
	synchronized public int cogerTicket() throws RemoteException{

		synchronized (O){
			while(main.getContador() >= maxcoches){
				try {
					O.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int contador = main.getContador();
			contador++;
			main.setContador(contador);
		}
		ticket++;
		System.out.println("Ticket numero "+ ticket +" expendido.");
		synchronized (O){
			System.out.println("Coches dentro: " + main.getContador());
		}
		while(pasarela.estado()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		pasarela.levantar_barrera();
		this.notifyAll();
		return ticket;
		
	}
	synchronized public void entregarTicket(int ticket) throws RemoteException{
		synchronized (O){
			int contador = main.getContador();
			contador--;
			main.setContador(contador);
		}
		System.out.println("Entregado ticket numero " + ticket);
		synchronized (O){
			System.out.println("Coches dentro: " + main.getContador());
		}
		pasarela.levantar_barrera();
		synchronized (O){
			O.notifyAll();
		}
		this.notifyAll();
	}
}