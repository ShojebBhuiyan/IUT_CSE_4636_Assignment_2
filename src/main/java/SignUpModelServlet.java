import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
    This will create new users for the shop and then redirect them back to the index page to login.
 */
public class SignUpModelServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO dao = new UserDAO(username, password);

        String result = dao.createUser();

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("<HTML><Body>" + result + "</Body></HTML>");
        RequestDispatcher rd = req.getRequestDispatcher("src/main/webapp/index.html");
        rd.forward(req, res);
    }
}
