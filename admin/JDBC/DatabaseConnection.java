package admin.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Registering the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing a database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            if (connection != null) {
                System.out.println("Connected to the database!");
                // Perform database operations here
            } else {
                System.out.println("Failed to connect to the database!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        } finally {
            try {
                // Closing the connection in the finally block to ensure it's always closed
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
