package Coche;

import java.rmi.RemoteException;

import Parking.Pasarela;
import Parking.Sensor;
import Parking.Ticketero;

public class Coche extends Thread {
	int ticket;
	Ticketero TEntrada;
	Ticketero TSalida;
	Sensor SEntrada;
	Sensor SSalida;
	Pasarela PEntrada;
	Pasarela PSalida;
	
	
	public Coche(Ticketero tEntrada, Ticketero tSalida,
			Sensor sEntrada, Sensor sSalida, Pasarela pEntrada, Pasarela pSalida) {
		TEntrada = tEntrada;
		TSalida = tSalida;
		SEntrada = sEntrada;
		SSalida = sSalida;
		PEntrada = pEntrada;
		PSalida = pSalida;
	}
	public void entrar(){
		PEntrada.pasar(ticket);
		SEntrada.activar();
	}
	public void salir(){
		PSalida.pasar(ticket);
		SSalida.activar();
	}
	public void run(){
		while(true){
			try {
				ticket = TEntrada.cogerTicket();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			entrar();
			try {
				Thread.sleep (1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			try {
				TSalida.entregarTicket(ticket);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			salir();
		}
	}
}