package TriviaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {
    }

    // * Face conexiunea cu baza de date si o returneaza.
    public static Connection getConnection() {

        String url = "jdbc:mysql://eu-cdbr-west-01.cleardb.com:3306/";
        String username = "ba881b86bcd929";
        String password = "30c279b4";


        try {
            if (connection == null)
                connection = DriverManager.getConnection(url, username, password);
            if(connection.isClosed())
                connection = DriverManager.getConnection(url, username, password);
            if(!connection.isValid(10))
                connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
