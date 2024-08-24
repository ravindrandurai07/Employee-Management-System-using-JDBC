import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class EmployeeInfo implements EmployeeManager{

    static Scanner sc = new Scanner(System.in);
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public static void main(String[] args) {

        System.out.println(DBConnection.getDBConnection());

        EmployeeManager employeeManager = new EmployeeInfo();
        employeeManager.addEmployee();
    }

    private static class DBConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/ravi";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "Tiger";

        private static Connection getDBConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException cnfe) {
                System.err.println("JDBC Driver class not found: " + cnfe.getMessage());
            } catch (SQLException sqle) {
                System.err.println("Failed to establish connection: " + sqle.getMessage());
            }
            return null;
        }
    }

    @Override
    public void addEmployee() {
        con = DBConnection.getDBConnection();
        int eid = getEmployeeId();sc.nextLine();
        String name = getName();
        byte age = getAge();
        int sal = getSalary();

        String query = "insert into emps values (?,?,?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, eid);
            ps.setInt(3, age);
            ps.setInt(4, sal);

            int count = ps.executeUpdate();
            System.out.println(count);
            con.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Could not add employee");
        }
    }

    private PreparedStatement prepSt(){
        return null;
    }

    @Override
    public void displayALlEmployees() {

    }

    @Override
    public void getEmployeeDetails(int id) {

    }

    @Override
    public void updateEmployee(int id) {

    }

    @Override
    public void removeEmployee(int id) {

    }
    //getting e-id from user
    private static int getEmployeeId(){
        System.out.print ("Enter Employee id : ");

        try {
            return sc.nextInt();
        }
        catch (InputMismatchException iae){
            sc.next();
            System.out.println("\t\tEnter a valid e_id");
        }
        return getEmployeeId();
    }
    //getting name from user
    private static String getName (){
        System.out.print ("Enter Employee Name : ");
        return sc.nextLine();
    }
    //getting sal from user
    private static int getSalary (){
        System.out.print("Enter Salary : ");
        try {
            return sc.nextInt();
        }
        catch (InputMismatchException iae){
            sc.next();
            System.out.println("\t\tEnter valid salary");
        }
        return getSalary();
    }
    //getting age from user
    private static byte getAge (){
        System.out.print("Enter Age : ");
        try {
            return sc.nextByte();
        }
        catch (InputMismatchException iae){
            sc.next();
            System.out.println("\t\t Enter a valid age");
        }
        return getAge();
    }
}
