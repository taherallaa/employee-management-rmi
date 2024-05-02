import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            String bindName = "employee";
            LocateRegistry.createRegistry(1099);
            Employee stub = new EmployeeImp();
            Naming.rebind(bindName, stub);
        } catch (RemoteException | MalformedURLException e) {
            System.out.println(e.getMessage());
       }
    }
}
