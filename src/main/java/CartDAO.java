import java.sql.*;

/*
    This is the cart object that a user will be using throughout the session. It's a Data Access Object class to interact with the backend database.
 */
public class CartDAO {
    String username;
    int A_quantity;
    int B_quantity;
    int C_quantity;
    int D_quantity;
    int E_quantity;

    public CartDAO(String user) { this.username = user; }

    public CartDAO(String user, int a, int b, int c, int d, int e) {
        this.username = user;
        this.A_quantity = a;
        this.B_quantity = b;
        this.C_quantity = c;
        this.D_quantity = d;
        this.E_quantity = e;
    }

    Connection connection = null;
    Statement statement;
    ResultSet resultSet;


    String url = "jdbc:mariadb://localhost:3306/WebArch_Assignment2";
    String user = "Shojeb";
    String pwd = "password";

    //  This modifies the cart contents in the database.
    public void updateCart() {
        String query = "replace into WebArch_Assignment2.CARTS(user, A_Quantity, B_Quantity, C_Quantity, D_Quantity, E_Quantity) " +
                "values (" + this.username + ", " + this.A_quantity + ", " + this.B_quantity + ", " + this.C_quantity + ", " + this.D_quantity + "," + this.E_quantity +");";
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //  This gets the cart contents from the database. This is used to restore cart status during login.
    public void getCart() {
        String query = "select * from WebArch_Assignment2.Carts where user = " + this.username + ";";
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            this.A_quantity = resultSet.getInt("A_Quantity");
            this.B_quantity = resultSet.getInt("B_Quantity");
            this.C_quantity = resultSet.getInt("C_Quantity");
            this.D_quantity = resultSet.getInt("D_Quantity");
            this.E_quantity = resultSet.getInt("E_Quantity");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
