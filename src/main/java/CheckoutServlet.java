import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
    This servlet updates the cart contents to zero items and also updates the database. Then the user can click the button to go back to the shop page.
 */
public class CheckoutServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CartDAO cart = new CartDAO(req.getSession().getAttribute("username").toString(), 0, 0, 0, 0, 0);
        cart.updateCart();
        req.getSession().setAttribute("cart", cart);

        PrintWriter out = res.getWriter();

        res.setContentType("text/html");

        out.println("<HTML>" +
                "<Body>Congratulations on your purchase!" +
                "<form method=\"get\" action=\"src/main/webapp/shop.html\">" +
                "<input type=\"submit\" value=\"Return to Shop\" />" +
                "</form>" +
                "</Body>" +
                "</HTML>");
    }
}
