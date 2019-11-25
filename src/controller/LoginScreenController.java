package controller;

import database.UserDao;
import entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoginScreenController {

    Logger userLogger = Logger.getLogger("userlog.txt");

    Stage stage;
    Parent scene;

    Boolean isValidUser = false;

    @FXML
    private Button login_button;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField username_field;
    static public User currentUser;

    @FXML
    void LoginButtonHandler(ActionEvent event) throws IOException, SQLException {

        String username = username_field.getText();
        String password = password_field.getText();


        currentUser = new User();

        //TODO: Change this back to the variable name
        currentUser.setUserName("test");
        currentUser.setUserPassword(password);
        currentUser.setUserId(1);


        isValidUser = true;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        if(isValidUser = true) {

            FileHandler userLF = new FileHandler("userlog.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            userLF.setFormatter(sf);
            userLogger.addHandler(userLF);
            userLogger.log(Level.INFO, "User " + currentUser.getUserName() + " logged in successfully");

        }




        //TODO: Uncomment this stuff for the final login and move the logger down here

       /* ObservableList<User> Users = UserDao.getActiveUsers();




        for (User user : Users) {

            if (user.getUserName().equals(username) && user.getUserPassword().equals(password)) {


                isValidUser = true;
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();


            } else {

                System.out.println("Incorrect user name or password!");
            }


        }
*/

    }

}
