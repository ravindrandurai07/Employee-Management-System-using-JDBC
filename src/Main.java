import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        EmployeeManager em = EmployeeInfo.getEmployeeManager();
        System.out.println("\t\tWelcome to Employee Management DataBase");
        System.out.println("\t\t---------------------------------------");

        boolean flag;
        do {
            System.out.println();
            StringBuilder sb = new StringBuilder();
            sb.append("1. View all Employee details\n");
            sb.append("2. Add an employee\n");
            sb.append("3. Get the details of an employee\n");
            sb.append("4. Update info of an employee\n");
            sb.append("5. Delete an employee from database\n");
            System.out.println(sb);
            try{
                switch (sc.nextInt()) {
                    case 1:
                        em.displayALlEmployees();
                        break;
                    case 2:
                        em.addEmployee();
                        break;
                    case 3:
                        System.out.print("Enter Employee id : ");
                        em.getEmployeeDetails(sc.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter Employee id : ");
                        em.updateEmployee(sc.nextInt());
                        break;
                    case 5:
                        System.out.print("Enter Employee id : ");
                        em.removeEmployee(sc.nextInt());
                        break;
                    default:
                        System.out.println("Enter valid input..");
                }
            }
            catch (InputMismatchException e){
                sc.next();
                System.out.println("Enter valid input..");
            }

            System.out.print("Do you want to continue (true / false) : ");
            flag = sc.nextBoolean();
            sc.nextLine();
        } while (flag);
        System.out.println("<END>");
    }
}