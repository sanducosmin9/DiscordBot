package TriviaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(String id, String name) {
        Connection connection = DBConnection.getConnection();

        int coins = 20;
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO TRIVIA (idUser, name, coins) VALUES (?, ?, ?)"))
        {
            st.setString(1,id);
            st.setString(2,name);
            st.setInt(3, coins);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void addAllUsers(List<String> id) {
//
//    }

    @Override
    public void addPoints(String id) {

    }

    @Override
    public void removePoints(String id) {

    }
}
