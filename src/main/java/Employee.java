import org.bson.Document;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

interface Employee extends Remote {

    String addEmployee(String name, String email, double salary, String department) throws RemoteException;

    // update employee
    String updateEmployeeSalary(String employeeID, String salary) throws RemoteException;

    String updateEmployeeDepartment(String employeeID, String department) throws RemoteException;

    String updateEmployeeEmail(String employeeID, String email) throws RemoteException;

    String deleteEmployee( String employeeID) throws RemoteException;

    String deleteEmployees() throws RemoteException;

    List<Document> getEmployees() throws RemoteException;

    Document getEmployee(String employeeID) throws RemoteException;
}
