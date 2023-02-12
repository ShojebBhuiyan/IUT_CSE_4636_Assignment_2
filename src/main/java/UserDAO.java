import java.sql.*;

/*
    It's a Data Access Object class to interact with the backend database to create new users or authenticate existing users.
 */
public class UserDAO {
    String name;
    String password;

    public UserDAO(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    Connection connection = null;
    Statement statement;
    ResultSet resultSet;


    String url = "jdbc:mariadb://localhost:3306/WebArch_Assignment2";
    String user = "Shojeb";
    String pwd = "password";

    public boolean userLogin() {
        String query = "select * from WebArch_Assignment2.USERS where username = " + this.name + " and password = SHA1(" + this.password + ");";
        boolean result = false;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet != null)
                result = true;
            else  result = false;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String createUser() {
        String query = "insert into WebArch_Assignment2.USERS(username, password) values ("+ this.name + ", SHA1(" + this.password + "));";
        String result = "";
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            result = resultSet.toString();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
