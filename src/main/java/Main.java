import org.bson.Document;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static Employee getStub() throws MalformedURLException {
        Employee result;
        String bindName = "employee";
        try {
            result = (Employee) Naming.lookup(bindName);
        } catch (NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) throws RemoteException {

        try {
            Employee stub = getStub();
            char letter;

            do {

                System.out.println("\nChoose from (1 to 5) what you want to do?");
                System.out.println(
                        """
                                1) Show all Employees.
                                2) Get just one Employee using ID.
                                3) Add  Employee.
                                4) Update Employee.
                                5) Remove Employee using ID.
                                6) Remove All Employee.
                                """

                );
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        List<Document> empolyess = stub.getEmployees();

                        if(empolyess.isEmpty()){
                            System.out.println("There no empolyees");
                        }else{
                            System.out.println(empolyess);
                        }
                        break;
                    case 2:
                        System.out.println("Enter employee ID: ");
                        String employeeID = scanner.next();
                        System.out.println(stub.getEmployee(employeeID));
                        break;
                    case 3:
                        addEmployee();
                        break;
                    case 4:
                        update_menu();
                        break;
                    case 5:
                        deleteEmployee();
                        break;
                    case 6:
                        System.out.println(stub.deleteEmployees());
                        break;
                    default:
                        System.out.println("Invalid option");
                }
                System.out.println("Do you want to do any processing. (Y,N)? ");
                letter = scanner.next().charAt(0);

            } while (letter == 'Y' || letter == 'y');
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEmployee() throws MalformedURLException {
        System.out.println("Enter employee ID to remove it: ");
        String employeeID = scanner.next();

        Employee stub = getStub();
        try {
            System.out.println(stub.deleteEmployee(employeeID));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void addEmployee() throws MalformedURLException {
        System.out.println("Enter your data [name, email, salary, department]: ");
        String name = scanner.next();
        String email = scanner.next();
        double salary = scanner.nextDouble();
        String department = scanner.next();
        Employee stub = getStub();
        try {
            System.out.println(stub.addEmployee(name, email, salary, department));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void update_menu(){
        char letter;
        do {
            System.out.println("\nChoose from (1 to 5) what you want to do?");
            System.out.println(
                    """
                            1) Update Salary.
                            2) Update Email.
                            3) Update Department.
                            """
            );
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    updateSalary();
                    break;
                case 2:
                    updateEmail();
                    break;
                case 3:
                    updateDepartment();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.println("Do you wanna update anything else? . (Y,N)? ");
            letter = scanner.next().charAt(0);
        } while (letter == 'Y' || letter == 'y');
    }

    private static void updateDepartment() {
        System.out.println("Enter employee ID, and new department for this employee ");
        String employeeID = scanner.next();
        String department = scanner.next();

        Employee stub;
        try {
            stub = getStub();
            System.out.println( stub.updateEmployeeDepartment(employeeID, department));
        } catch (MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    private static void updateEmail() {
        System.out.println("Enter employee ID, and new email for this employee ");
        String employeeID = scanner.next();
        String email = scanner.next();

        Employee stub;
        try {
            stub = getStub();
            System.out.println( stub.updateEmployeeDepartment(employeeID, email));
        } catch (MalformedURLException | RemoteException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void updateSalary() {
        System.out.println("Enter employee ID, and new salary for this employee ");
        String employeeID = scanner.next();
        String salary = scanner.next();

        Employee stub;
        try {
            stub = getStub();
            System.out.println( stub.updateEmployeeSalary(employeeID, salary));
        } catch (MalformedURLException | RemoteException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
// 662d58c2d5589e510c29bb9a