package admin.JDBC;

import java.sql.*;
import com.google.gson.Gson;
import static spark.Spark.*;

public class backend {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bike_rental";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    public static void main(String[] args) {
        // Configure Spark
        port(8080);

        // Define API endpoints
        path("/api", () -> {
            // Get bike list
            get("/bikes", (request, response) -> {
                try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                    // Use JDBC to fetch bike data from the database
                    ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM bikes");
                    // Process the result set and convert to JSON
                    // This is a simplified example; in a real-world scenario, you'd use a library like Jackson or Gson
                    // to convert the ResultSet to JSON
                    Gson gson = new Gson();
                    String jsonResult = gson.toJson(resultSet);
                    // Return the JSON response
                    return jsonResult;
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.status(500);
                    return "{'error': 'Internal Server Error'}";
                }
            });

            // Add more endpoints for other CRUD operations
            // ...

        });
    }
}

