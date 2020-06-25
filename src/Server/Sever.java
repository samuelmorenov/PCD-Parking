package Server;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class Sever {
	public static void main(String args[]) {

		   String identificador; 
		    
		    try {
		           identificador = args[0];
		    } catch (ArrayIndexOutOfBoundsException e) {
		              identificador="MyCounter";
		              System.out.println("\nNo se ha especificado nombre objeto servidor");
		              System.out.println("   usando nombre por defecto " + identificador + "\n");
			     
		    }
		   // Create and install the security manager
		   System.setSecurityManager(new RMISecurityManager());

		   try
		   {
		     // Create CountRMIImpl
		    ContadorImp myCount = new ContadorImp();
			Contador stub=(Contador) UnicastRemoteObject.exportObject(myCount, 0);
			Registry registry= LocateRegistry.getRegistry();
			registry.rebind(identificador, stub);
		    System.out.println("CountRMI Server " + identificador + " ready.");

		   } catch (Exception e)
		   { System.out.println("Exception: " + e.getMessage());
		     e.printStackTrace();
		   }
		 }

}
