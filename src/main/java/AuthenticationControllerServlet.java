import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/*
This will authenticate users.
 */
public class AuthenticationControllerServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String choice = req.getParameter("choice");

        if (choice == "Login") {
            RequestDispatcher rd = req.getRequestDispatcher("LoginModelServlet");
            rd.forward(req, res);
        }
        else if (choice == "SignUp") {
            RequestDispatcher rd = req.getRequestDispatcher("SignUpModelServlet");
            rd.forward(req, res);
        }
        else {
            req.getSession().invalidate();
            RequestDispatcher rd = req.getRequestDispatcher("src/main/webapp/index.html");
            rd.forward(req, res);
        }
    }
}