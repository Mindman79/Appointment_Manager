package controller;

import database.AppointmentDao;
import database.CustomerDao;
import database.UserDao;
import entity.Appointment;
import entity.Customer;
import entity.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;

public class ReportsScreenController {


    Stage stage;
    Parent scene;

    private static ZoneId localZoneId = ZoneId.systemDefault();

    private final DateTimeFormatter DTformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea report_textarea;

    @FXML
    private Button customers_button;

    @FXML
    private Button appointments_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button logs_button;

    @FXML
    private ToggleGroup report_toggle_grp;

    @FXML
    private RadioButton report_types_button;

    @FXML
    private RadioButton report_consultant_button;

    @FXML
    private RadioButton active_customers_button;

    @FXML
    void report_types_button_handler(ActionEvent event) {


        try {
            report_textarea.clear();
            ObservableList<Appointment> appointmentTypes = AppointmentDao.getAppointmentTypes();
            StringBuilder report = new StringBuilder();


            for (Appointment appointment : appointmentTypes) {

                report.append("Appointment Type Found: " + appointment.getType());
                report.append(System.getProperty("line.separator"));

            }

            int count = appointmentTypes.size();
            report.append("Total of appointment types for the month: " + count);


            String finishedReport = report.toString();

            report_textarea.setText(finishedReport);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void report_consultant_button_handler(ActionEvent event) throws SQLException {


        try {
            report_textarea.clear();
            ObservableList<Appointment> appointmentsByUser = AppointmentDao.getAllAppointmentsByUser();

            StringBuilder report = new StringBuilder();


            User user = new User();

            for (Appointment appointment : appointmentsByUser) {


                user = UserDao.getUserById(appointment.getUserId());
                report.append("Appointment for user: " + user.getUserName() + ", titled: "  + appointment.getTitle() + ", starting at " + appointment.getStart().toLocalDateTime().format(DTformatter)+ " and lasting until " + appointment.getEnd().toLocalDateTime().format(DTformatter));
                report.append(System.getProperty("line.separator"));

            }


            String finishedReport = report.toString();

            report_textarea.setText(finishedReport);
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (NullPointerException e) {
            e.printStackTrace();
            alertGenerator("Data required to the run this report is not available. Please populate the database first.");
        }


    }

    @FXML
    void active_customers_button_handler(ActionEvent event) {


        try {
            report_textarea.clear();
            ObservableList<Customer> activeCustomers = CustomerDao.getAllCustomers();
            StringBuilder report = new StringBuilder();


            for (Customer customer : activeCustomers) {

                report.append("Customer " + customer.getCustomerName() + " is active");
                report.append(System.getProperty("line.separator"));

            }


            String finishedReport = report.toString();

            report_textarea.setText(finishedReport);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    @FXML
    void customers_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }



    @FXML
    void exit_button_handler(ActionEvent event) {

        Platform.exit();

    }

    @FXML
    void logs_button_handler(ActionEvent event) {

        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "userlog.txt");
            pb.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    void appointments_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @FXML
    void initialize() {




    }

    public void alertGenerator(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(message);
        alert.setHeaderText(message);
        alert.showAndWait();


    }

}
