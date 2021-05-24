package TriviaDB;

import java.util.List;

public interface UserDAO {
    void addUser(String id, String name);
//    void addAllUsers(List<String> id);
    void addPoints(String id);
    void removePoints(String id);
}
