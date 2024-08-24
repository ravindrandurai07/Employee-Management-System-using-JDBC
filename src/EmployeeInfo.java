import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class EmployeeInfo implements EmployeeManager{

    static Scanner sc;
    Connection con;

    public EmployeeInfo(Scanner sc){
        EmployeeInfo.sc = sc;
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
        sc.nextLine();
        con = DBConnection.getDBConnection();
        String name = getName();
        int eid = getEmployeeId();sc.nextLine();
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
            System.out.println(count + " rows created");
            con.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("\t\tCould not add employee");
        }
    }

    @Override
    public void displayALlEmployees() {
        try {
            con = DBConnection.getDBConnection();
            String query = "select * from emps;";
            assert con != null;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("Name\t\t" + "id\t\t" + "age\t " + "salary");
            System.out.println("-------------------------------");
            while (rs.next()){
                System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t " + rs.getInt(4));
            }
            con.close();
        }
         catch (SQLException e) {
             System.out.println("\t\tCould not fetch details");
        }
    }

    @Override
    public void getEmployeeDetails(int id) {
        try {
            con = DBConnection.getDBConnection();
            String query = "select * from emps where id =?;";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Name\t\t" + "id\t\t" + "age\t " + "salary");
            System.out.println("-------------------------------");
            System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t " + rs.getInt(4));
            con.close();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("\t\tCould not fetch details");
        }
    }

    @Override
    public void updateEmployee(int id) {
        sc.nextLine();

        try{
            con = DBConnection.getDBConnection();
            String query = "UPDATE emps SET name =?, age =?, salary =? WHERE id =?;";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, getName());
            ps.setInt(2, getAge());
            ps.setInt(3, getSalary());
            ps.setInt(4, id);

            int n = ps.executeUpdate();
            System.out.println(n + " rows modified");
            con.close();
            ps.close();
        }
        catch (SQLException e){
            System.out.println("\t\tCould not update");
        }
    }

    @Override
    public void removeEmployee(int id) {

        String query = "DELETE FROM emps WHERE id = ?;";
        try {
            con = DBConnection.getDBConnection();
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            System.out.println(n +  " rows deleted");
            con.close();
            ps.close();

        }
        catch (SQLException e){
            System.out.println("\t\tCould not remove ");
        }
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
