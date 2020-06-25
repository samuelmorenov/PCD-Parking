package Cliente;
import java.rmi.*;

import java.rmi.registry.*;
import java.rmi.server.*;

import Parking.Pasarela;
import Parking.Sensor;
import Parking.Ticketero;
import Server.Contador;

import Coche.Coche;

public class Main {

	
	static Object O = new Object();
	static Contador myCount;
		
	public int getContador() throws RemoteException{
		return myCount.get();
	}
	public void setContador(Integer C) throws RemoteException{
		myCount.set(C);
	}
	  
	public static void registrarse(String args[]){
		try{
			myCount = (Contador)Naming.lookup("rmi://" + args[0] + "/" + args[1]);
			myCount.set(0);
		} catch(Exception e){
			System.err.println("System Exception" + e);
		}
	}


public static void main(String[] args) {
	Main M = new Main();
	M.registrarse(args);
	Pasarela PEntrada = new Pasarela("de ENTRADA");
	Pasarela PSalida = new Pasarela("de SALIDA");
	Sensor SEntrada = new Sensor(PEntrada);
	Sensor SSalida = new Sensor(PSalida);
	Ticketero TEntrada = new Ticketero(M, PEntrada, O);
	Ticketero TSalida = new Ticketero(M, PSalida, O);
	for(int i=0; i<25;i++){
		new Coche(TEntrada, TSalida, SEntrada, SSalida, PEntrada, PSalida).start();
	}
	
}	  	
}
