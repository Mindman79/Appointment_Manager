package database;

import entity.Customer;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static database.DBConnection.conn;

public class UserDao {

    public static ObservableList<User> getActiveUsers() throws SQLException {

        try {
        ObservableList<User> activeUsers = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM user WHERE active = 1";

        QueryManager.makeQuery(sqlStatement);
        ResultSet result = QueryManager.getResult();


        while (result.next()) {


            User activeUser = new User();
            activeUser.setUserId(result.getInt("userId"));
            activeUser.setUserName(result.getString("userName"));
            activeUser.setUserPassword(result.getString("password"));
            activeUser.setActive(result.getBoolean("active"));

            activeUsers.add(activeUser);






        }

        return activeUsers;
    } catch (
    SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
        return null;
    }
}

    public static User getUserById(int id) throws SQLException {

        User user = new User();

        try {

            String yo = "SELECT * FROM user WHERE userId = ?";

            PreparedStatement statement = conn.prepareStatement(yo);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();


            while (result.next()) {


                //User user = new User();
                user.setUserId(result.getInt("userId"));
                user.setUserName(result.getString("userName"));
                user.setUserPassword(result.getString("password"));
                user.setActive(result.getBoolean("active"));








            }

            return user;
        } catch (
                SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }




    }

