package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import database.AppointmentDao;
import database.CustomerDao;
import entity.Appointment;
import entity.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utils.DateTime;

import static controller.LoginScreenController.currentUser;
import static java.util.Calendar.NARROW_FORMAT;
import static java.util.Calendar.PM;

public class AppointmentAddScreenController {

    Stage stage;
    Parent scene;

    private static ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField title_field;

    @FXML
    private TextField location_field;

    @FXML
    private TextField type_field;

    @FXML
    private TextField url_field;

    @FXML
    private TextField description_field;

    @FXML
    private ComboBox<Customer> customer_combo_box;

    @FXML
    private TextField contact_field;

    @FXML
    private DatePicker start_date_selector;

    @FXML
    private DatePicker end_date_selector;

    @FXML
    private TextField start_time_field;

    @FXML
    private TextField end_time_field;


    @FXML
    void cancel_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void save_button_handler(ActionEvent event) throws SQLException, IOException {


        Appointment appointment = new Appointment();

        try {

            //Set business hours open and close times
            LocalTime openTime = LocalTime.of(7, 00);
            LocalTime closeTime = LocalTime.of(19, 00);

            //Get start date
            LocalDate startDate = start_date_selector.getValue();
            LocalTime startTime = LocalTime.parse(start_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a"));
            ZonedDateTime start = ZonedDateTime.of(startDate, startTime, localZoneId);

            //Get end date
            LocalDate endDate = end_date_selector.getValue();
            LocalTime endTime = LocalTime.parse(end_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a"));
            ZonedDateTime end = ZonedDateTime.of(endDate, endTime, localZoneId);


            //Appointment field validator (checks that start/end times are within the hours of 7:00 AM - 7:00 PM and that fields are not blank, etc. Other values are checked via the try/catch block.
            if (startTime.isBefore(openTime)) {

                alertGenerator("Appointment must start no earlier than " + openTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

            } else if (endTime.isAfter(closeTime)) {

                alertGenerator("Appointment must end no later than " + closeTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

            } else if (startDate.isBefore(LocalDate.now())) {

                alertGenerator("Appointment cannot start earlier than today's date!");

            } else if (endDate.isBefore(LocalDate.now())) {

                alertGenerator("Appointment cannot end earlier than today's date!");

            } else {

                appointment.setCustomerId(customer_combo_box.getValue().getCustomerId());
                appointment.setTitle(title_field.getText());
                appointment.setDescription(description_field.getText());
                appointment.setLocation(location_field.getText());
                appointment.setContact(contact_field.getText());
                appointment.setType(type_field.getText());
                appointment.setUrl(url_field.getText());
                appointment.setStart(start);
                appointment.setEnd(end);


                //Appointment overlap checker
                Appointment overlappingAppointment = overlapChecker(appointment);
                if (overlappingAppointment != null) {


                    Customer overlapCustomer = CustomerDao.getCustomerById(overlappingAppointment.getCustomerId());

                    Alert overlappingAlert = new Alert(Alert.AlertType.INFORMATION);
                    overlappingAlert.setTitle("You have an overlapping appointment!");
                    overlappingAlert.setHeaderText("Appointment is with " + overlapCustomer.getCustomerName() + " from " + overlappingAppointment.getStart().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")) + " to " + overlappingAppointment.getEnd().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
                    overlappingAlert.showAndWait();




                } else {


                    AppointmentDao.addAppointment(appointment);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();


                }


            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            alertGenerator("Start and/or end time cannot be blank, and must be formatted correctly! (HH:MM AM/PM)");

        } catch (NullPointerException e) {
            e.printStackTrace();
            alertGenerator("Customer must be selected!");
        }
    }

    @FXML
    void initialize() {

        ObservableList<Customer> customers = CustomerDao.getAllCustomers();


        customer_combo_box.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer.getCustomerName();
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });

        customer_combo_box.setItems(customers);

        start_date_selector.setValue(LocalDate.now());
        end_date_selector.setValue(LocalDate.now());



    }


    public void alertGenerator(String message) {

        Alert apptAlert = new Alert(Alert.AlertType.INFORMATION);
        apptAlert.setTitle(message);
        apptAlert.setHeaderText(message);
        apptAlert.showAndWait();


    }

    public Appointment overlapChecker(Appointment appointmentToCheck) {

        Appointment appointmentChecked = AppointmentDao.overlappingAppointmentAdd(appointmentToCheck);

        if (appointmentChecked.getAppointmentId() != 0) {

            return appointmentChecked;

        } else {

            return null;
        }

    }
}
