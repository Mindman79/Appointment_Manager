package controller;

import database.AppointmentDao;
import database.CustomerDao;
import database.UserDao;
import entity.Appointment;
import entity.Customer;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
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
    private String loginError;
    private String upcomingAppt;


    //Two means of exception control in this method. Try/catch and assertion. See line 80 for the assertion test and associated error if it fails (please enable assertion errors in IntelliJ to see this). This also displays a user error alert box via the try/catch block.
    @FXML
    void LoginButtonHandler(ActionEvent event) throws IOException, SQLException, AssertionError {

        String username = username_field.getText();
        String password = password_field.getText();


        currentUser = new User();


        currentUser.setUserName(username);
        currentUser.setUserPassword(password);
        currentUser.setUserId(1);


        ObservableList<User> Users = UserDao.getActiveUsers();


        //Lambda #1 to simplify the user login loop
        Users.forEach((user -> {

            try {

                assert user.getUserName().equals(username) && user.getUserPassword().equals(password) : "Incorrect username or password!";


                isValidUser = true;


                if (isValidUser = true) {

                    //Stage
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                    //Logging
                    FileHandler userLF = new FileHandler("userlog.txt", true);
                    SimpleFormatter sf = new SimpleFormatter();
                    userLF.setFormatter(sf);
                    userLogger.addHandler(userLF);
                    userLogger.log(Level.INFO, "User " + currentUser.getUserName() + " logged in successfully");


                    //Appointment alert
                    try {
                        Appointment appointment = AppointmentDao.getUpcomingAppointments();
                        if (!(appointment.getAppointmentId() == 0)) {

                            Customer customer = CustomerDao.getCustomerById(appointment.getCustomerId());
                            Alert apptAlert = new Alert(Alert.AlertType.INFORMATION);
                            apptAlert.setTitle(upcomingAppt);
                            apptAlert.setHeaderText(upcomingAppt);
                            apptAlert.setContentText(appointment.getStart().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")) + " with " + customer.getCustomerName());
                            apptAlert.showAndWait();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (AssertionError e) {
                e.printStackTrace();
                Alert loginAlert = new Alert(Alert.AlertType.INFORMATION);
                loginAlert.setTitle(loginError);
                loginAlert.setHeaderText(loginError);
                System.out.println(loginError);
                loginAlert.showAndWait();


            }

        }))
        ;
    }


    @FXML
    void initialize() throws SQLException {


        UserDao.setSessionTimeout();

        //Locale related items
        Locale espanol = new Locale("es", "ES");
        Locale german = new Locale("de", "DE");
        Locale userLocale = Locale.getDefault();

        ResourceBundle rb = ResourceBundle.getBundle("locale/Nat", userLocale);

        username_field.setPromptText(rb.getString("username"));
        password_field.setPromptText(rb.getString("password"));
        login_button.setText(rb.getString("login"));
        loginError = rb.getString("loginerror");
        upcomingAppt = rb.getString("upcomingappt");

    }


    public boolean loginTester(String un, String pw) throws SQLException {

        boolean status = false;

        //ObservableList<User> UsersMatch = UserDao.getActiveUsers();

        ObservableList<User> UsersMatch = FXCollections.observableArrayList();


        User user = new User();

        user.setUserName("test");
        user.setUserPassword("test");

        UsersMatch.add(user);


        for (User user2 : UsersMatch) {

            //System.out.println(user2.getUserName());

            if (user2.getUserName().equals(un) && user2.getUserPassword().equals(pw)) {

                status = true;

            } else {

                status = false;
            }



        }

        return status;

    }

}











