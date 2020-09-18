package controller;

import database.AppointmentDao;
import database.CustomerDao;
import database.UserDao;
import entity.Appointment;
import entity.Customer;
import entity.User;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

            //report.append(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","Identifier","Category","Level","Space","Type","Dimension","Limits"));
            report.append("--------------------------------------------------------------------------------------------");
            report.append(System.getProperty("line.separator"));
            report.append(String.format("%-11s%-28s%-20s%-35s%-25s","User","Title","Customer","Start Time","End Time"));
            report.append(System.getProperty("line.separator"));
            report.append("--------------------------------------------------------------------------------------------");


            User user;
            Customer customer;


            for (Appointment appointment : appointmentsByUser) {




                user = UserDao.getUserById(appointment.getUserId());
                customer = CustomerDao.getCustomerById(appointment.getCustomerId());

                report.append(System.getProperty("line.separator"));
                report.append(String.format("%-12s%-19s%-20s%-25s%-25s", user.getUserName(), appointment.getTitle(), customer.getCustomerName(), appointment.getStart().toLocalDateTime().atZone(localZoneId).format(DTformatter), appointment.getEnd().toLocalDateTime().atZone(localZoneId).format(DTformatter)));
                //report.append(user.getUserName() + "         " + appointment.getTitle() + "     " + appointment.getStart().toLocalDateTime().format(DTformatter) + "        " + appointment.getEnd().toLocalDateTime().format(DTformatter));
                report.append(System.getProperty("line.separator"));

//
//                report.append("Appointment for user: " + user.getUserName() + ", titled: " + appointment.getTitle() + ", starting at " + appointment.getStart().toLocalDateTime().format(DTformatter) + " and lasting until " + appointment.getEnd().toLocalDateTime().format(DTformatter));
//                report.append(System.getProperty("line.separator"));

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

        String active = "True";

        try {
            report_textarea.clear();
            ObservableList<Customer> activeCustomers = CustomerDao.getAllCustomers();
            StringBuilder report = new StringBuilder();


            report.append("---------------------------------------------------");
            report.append(System.getProperty("line.separator"));
            report.append(String.format("%-23s%-20s%-20s", "Customer Name","Active","Last Update"));
            report.append(System.getProperty("line.separator"));
            report.append("---------------------------------------------------");


            for (Customer customer : activeCustomers) {

                if (customer.getActive() == true) {

                    active = "True";

                } else {

                    active = "False";

                }


                report.append(System.getProperty("line.separator"));
                report.append(String.format("%-28s%-20s%-20s",customer.getCustomerName(), active, customer.getLastUpdate().toLocalDateTime().minusHours(6).atZone(localZoneId).format(DTformatter)));
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
        } catch (IOException e) {
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
