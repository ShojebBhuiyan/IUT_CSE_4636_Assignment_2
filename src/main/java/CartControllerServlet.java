import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    This will help send users to their cart and also to update the cart and checkout.
 */
public class CartControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String choice = req.getParameter("choice");

        RequestDispatcher rd = null;

        if (choice == "View Cart")
        {
            rd = req.getRequestDispatcher("CartServlet");
        }
        else if (choice == "Confirm") {
            rd = req.getRequestDispatcher("CheckoutServlet");
        }
        rd.forward(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String choice = req.getParameter("choice");

        RequestDispatcher rd;
        if (choice == "View Cart") {
            rd = req.getRequestDispatcher("CartServlet");
        }

        else {
            rd = req.getRequestDispatcher("src/main/webapp/shop.html");
        }
        rd.forward(req, res);
    }
}
