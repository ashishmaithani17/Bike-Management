package admin.JDBC;

import java.sql.*;

public class EmployeeDAO {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Establishing a database connection
    static {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create operation
    public static void createEmployee(String name, String position, double salary) {
        try {
            String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, position);
                preparedStatement.setDouble(3, salary);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read operation
    public static void readEmployees() {
        try {
            String sql = "SELECT * FROM employees";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    System.out.println("Employee ID: " + resultSet.getInt("id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Position: " + resultSet.getString("position"));
                    System.out.println("Salary: " + resultSet.getDouble("salary"));
                    System.out.println("----------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update operation
    public static void updateEmployee(int id, String newPosition, double newSalary) {
        try {
            String sql = "UPDATE employees SET position = ?, salary = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newPosition);
                preparedStatement.setDouble(2, newSalary);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation
    public static void deleteEmployee(int id) {
        try {
            String sql = "DELETE FROM employees WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        createEmployee("John Doe", "Developer", 60000);
        readEmployees();
        updateEmployee(1, "Senior Developer", 75000);
        readEmployees();
        deleteEmployee(1);
        readEmployees();
    }
}

