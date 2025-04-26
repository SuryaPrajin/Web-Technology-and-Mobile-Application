import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String dateOfBirth = request.getParameter("dob");

        if (name == null || dateOfBirth == null || dateOfBirth.length() < 10) {
            out.println("<html><body><h2>Missing or invalid input</h2></body></html>");
            return;
        }

        String yearString = dateOfBirth.substring(0, 4);
        int birthYear = Integer.parseInt(yearString);
        int age = 2025 - birthYear;

        out.println("<html><body><h1>Hello, " + name + "! You are " + age + " now</h1></body></html>");
    }
}
