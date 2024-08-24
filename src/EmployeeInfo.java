import java.sql.*;

public class EmployeeInfo implements EmployeeManager{

    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public static void main(String[] args) {

        System.out.println(new DBConnection().getDBConnection());
    }

    private static class DBConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/ravi";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "Tiger";

        private Connection getDBConnection() {
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
}
