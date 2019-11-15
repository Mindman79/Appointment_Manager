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

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoginScreenController {

    Logger userLog = Logger.getLogger("userlog.txt");

    Stage stage;
    Parent scene;

    Boolean isValidUser;

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


        currentUser.setUserName(username);
        currentUser.setUserPassword(password);
        currentUser.setUserId(1);





      /*  FileHandler userLogFH = new FileHandler("userlog.txt", true);
        SimpleFormatter sf = new SimpleFormatter();
        userLogFH.setFormatter(sf);
        userLog.addHandler(userLogFH);
        userLog.setLevel(Level.INFO);
*/

        ObservableList<User> Users = UserDao.getActiveUsers();


        //TODO: Login loop

        for (User user : Users) {

            if (user.getUserName().equals(username) && user.getUserPassword().equals(password)) {

//                System.out.println(user.getUserName());
//                System.out.println(user.getUserPassword());
                isValidUser = true;
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();


            } else {

                System.out.println("Incorrect user name or password!");
            }


        }


    }

}
