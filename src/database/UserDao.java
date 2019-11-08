package database;

import entity.Customer;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

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




    }

