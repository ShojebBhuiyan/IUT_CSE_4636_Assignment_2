import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
    This servlet will first update the cart after picking items for the shop. The cart updates will be sent to the database. Afterwards, it'll display the cart contents.
    From there users can proceed to checkout, or they can update their cart further.
 */
public class CartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        CartDAO cartDAO = (CartDAO) req.getSession().getAttribute("cart");
        int[] items = {cartDAO.A_quantity, cartDAO.B_quantity, cartDAO.C_quantity, cartDAO.D_quantity, cartDAO.E_quantity};
        int total = 0;

        out.println("<HTML>" +
                "<Body>" +
                "<Table>" +
                "<thead>" +
                "<td>Item</td><td>Quantity</td><td>Total</td>" +
                "</thead>" +
                "<tbody>");

        // To only show the items available in the cart
        for (int i = 0; i < 5; i++) {
            if (items[i] != 0)
            {
                out.println("<tr>" +
                        "<td>" + ('A' +  i) + "</td>" +
                        "<td>" + items[i] + "</td>" +
                        "<td>" + ((i + 1) * 100 * items[i]) + "</td>" +
                        "</tr>");
                total += (i + 1) * 100 * items[i];
            }
        }
        out.println("</tbody>" +
                "</table>" +
                "<br>" +
                "Total: " + total + "<br>" +
                "<form method=\"get\" action=\"CartControllerServlet\">" +
                "<input type=\"submit\" name=\"option\" value=\"Confirm\" />" +
                "</form>" +
                "<form method=\"post\" action=\"CartControllerServlet\">" +
                "<input type=\"submit\" name=\"option\" value=\"Update Cart\" />" +
                "</form>" +
                "</body>" +
                "</html>");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int a = Integer.parseInt(req.getParameter("A_Quantity"));
        int b = Integer.parseInt(req.getParameter("B_Quantity"));
        int c = Integer.parseInt(req.getParameter("C_Quantity"));
        int d = Integer.parseInt(req.getParameter("D_Quantity"));
        int e = Integer.parseInt(req.getParameter("E_Quantity"));
        String user = req.getSession().getAttribute("username").toString();

        CartDAO cartDAO = new CartDAO(user, a, b, c, d, e);

        cartDAO.updateCart();

        req.getSession().setAttribute("cart", cartDAO);

        doGet(req, res);
    }
}
