package Server;

import java.rmi.*;
public class ContadorImp implements Contador{
	  private int contador;

	  public ContadorImp(String name){
	    super();
	    contador=0;
	  }
	   public ContadorImp() {
	    super();
	    contador=0;
	  }
	  public int get() { 
		  return contador;
	  }
	  public  void set(int val) { contador = val;
	  }

	  public int increment() { contador++;
	    return contador;
	  }
	  public int decrementar() { contador--;
	  return contador;
	}

}
