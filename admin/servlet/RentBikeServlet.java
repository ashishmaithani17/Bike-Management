import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RentBikeServlet")
public class RentBikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming a bike is available for rent
        String bikeId = request.getParameter("bikeId");
        String userName = request.getParameter("userName");

        // Perform validation, update database, etc.

        // Send response to the user
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Bike Rented Successfully</h2>");
        out.println("<p>Bike ID: " + bikeId + "</p>");
        out.println("<p>Rented by: " + userName + "</p>");
        out.println("</body></html>");
    }
}
