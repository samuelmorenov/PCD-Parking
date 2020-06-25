package Server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Contador extends Remote{

    int get() throws RemoteException;
    void set(int _val) throws RemoteException;
}
