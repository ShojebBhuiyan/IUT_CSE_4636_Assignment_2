import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/*
    This will check database with user info and login if valid.
 */
public class LoginModelServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO user_dao = new UserDAO(username, password);

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        boolean isValid = user_dao.userLogin();

        if (isValid) {
            out.println("<HTML><BODY>Logged In Successfully!</BODY></HTML>");
            HttpSession session = req.getSession();

            session.setAttribute("username", username);

            //  This is for the optional task. This will reload previous cart contents after login.
            CartDAO cartDAO = new CartDAO(username);
            cartDAO.getCart();

            session.setAttribute("cart", cartDAO);

            RequestDispatcher rd = req.getRequestDispatcher("src/main/webapp/shop.html");
            rd.forward(req, res);
        }
        else {
            out.println("<HTML><BODY>User not found!</BODY></HTML>");
            RequestDispatcher rd = req.getRequestDispatcher("src/main/webapp/index.html");
            rd.forward(req, res);
        }
    }
}
