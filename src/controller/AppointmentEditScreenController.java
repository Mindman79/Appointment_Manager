package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

import static controller.LoginScreenController.currentUser;

public class AppointmentEditScreenController {

    private static ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

    private int apptId;


    Stage stage;
    Parent scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Customer> customer_combo_box;

    @FXML
    private TextField title_field;

    @FXML
    private TextField description_field;

    @FXML
    private TextField location_field;

    @FXML
    private TextField contact_field;

    @FXML
    private TextField type_field;

    @FXML
    private TextField url_field;

    @FXML
    private DatePicker start_date_selector;

    @FXML
    private TextField start_time_field;

    @FXML
    private DatePicker end_date_selector;

    @FXML
    private TextField end_time_field;

    @FXML
    private Button save_button;

    @FXML
    private Button cancel_button;

    @FXML
    void cancel_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void save_button_handler(ActionEvent event) throws SQLException, IOException {

        System.out.println("appointment ID test 2: " + apptId);
        Appointment appointment = AppointmentDao.getAppointmentById(apptId);

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



            //Appointment validator checker (checks that start/end times are within the hours of 7:00 AM - 7:00 PM and that fields are not blank, etc. Other values are check via the try/catch block
            if (startTime.isBefore(openTime)) {

                alertGenerator("Appointment must start no earlier than " + openTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

            } else if (endTime.isAfter(closeTime)) {

                alertGenerator("Appointment must end no later than " + endTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

            } else if (startDate.isBefore(LocalDate.now())) {

                alertGenerator("Appointment cannot start earlier than today's date!");

            } else if (endDate.isBefore(LocalDate.now())) {

                alertGenerator("Appointment cannot end earlier than today's date!");

            } else {




                appointment.setCustomerId(customer_combo_box.getValue().getCustomerId());
                appointment.setAppointmentId(apptId);
                appointment.setTitle(title_field.getText());
                appointment.setDescription(description_field.getText());
                appointment.setLocation(location_field.getText());
                appointment.setContact(contact_field.getText());
                appointment.setType(type_field.getText());
                appointment.setUrl(url_field.getText());
                appointment.setStart(start);
                appointment.setEnd(end);

//                AppointmentDao.updateAppointment(appointment);
//
//                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//                scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
//                stage.setScene(new Scene(scene));
//                stage.show();
//

                Appointment overlappingAppointment = overlapChecker(appointment);
                if (overlappingAppointment != null) {


                    //System.out.println("Appointment ID test: " + appointmentOverlap.getAppointmentId());

                    Customer overlapCustomer = CustomerDao.getCustomerById(overlappingAppointment.getCustomerId());

                    Alert overlappingAlert = new Alert(Alert.AlertType.INFORMATION);
                    overlappingAlert.setTitle("You have an overlapping appointment!");
                    overlappingAlert.setHeaderText("Appointment is with " + overlapCustomer.getCustomerName() + " from " + overlappingAppointment.getStart().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")) + " to " + overlappingAppointment.getEnd().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
                    overlappingAlert.showAndWait();




                } else {

                    appointment.setCustomerId(customer_combo_box.getValue().getCustomerId());
                    appointment.setAppointmentId(apptId);
                    appointment.setTitle(title_field.getText());
                    appointment.setDescription(description_field.getText());
                    appointment.setLocation(location_field.getText());
                    appointment.setContact(contact_field.getText());
                    appointment.setType(type_field.getText());
                    appointment.setUrl(url_field.getText());
                    appointment.setStart(start);
                    appointment.setEnd(end);

                    AppointmentDao.updateAppointment(appointment);

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

        } catch (
                DateTimeParseException e) {
            e.printStackTrace();
            alertGenerator("Start and/or end time cannot be blank, and must be formatted correctly! (HH:MM AM/PM)");

        } catch (NullPointerException e) {
            e.printStackTrace();
            alertGenerator("Customer must be selected!");
        }


    }

    @FXML
    void initialize() {

        customer_combo_box.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {

                return customer.getCustomerName();

            }

            @Override
            public Customer fromString(String string) {

                return customer_combo_box.getValue();

            }
        });

    }


    public int receiveAppointment(Appointment appointment) {


        apptId = appointment.getAppointmentId();

        System.out.println("appointment ID test: " + apptId);

        customer_combo_box.setItems(CustomerDao.getAllCustomers());

        int selectedCustId = appointment.getCustomerId();
        Customer selectedCust = CustomerDao.getCustomerById(selectedCustId);

        customer_combo_box.getSelectionModel().select(selectedCust);
        customer_combo_box.setValue(selectedCust);

        title_field.setText(appointment.getTitle());
        description_field.setText(appointment.getDescription());
        location_field.setText(appointment.getLocation());
        contact_field.setText(appointment.getContact());
        type_field.setText(appointment.getType());
        url_field.setText(appointment.getUrl());

        start_time_field.setText(appointment.getStart().format(DateTimeFormatter.ofPattern("hh:mm a")));
        end_time_field.setText(appointment.getEnd().format(DateTimeFormatter.ofPattern("hh:mm a")));
        start_date_selector.setValue(appointment.getStart().toLocalDate());
        end_date_selector.setValue(appointment.getEnd().toLocalDate());

        return apptId;

    }


    public void alertGenerator(String message) {

        Alert apptAlert = new Alert(Alert.AlertType.INFORMATION);
        apptAlert.setTitle(message);
        apptAlert.setHeaderText(message);
        apptAlert.showAndWait();


    }

    public Appointment overlapChecker(Appointment appointmentToCheck) {

        Appointment appointmentChecked = AppointmentDao.overlappingAppointment(appointmentToCheck);

        if (appointmentChecked.getAppointmentId() != 0) {

            return appointmentChecked;

        } else {

            return null;
        }

    }
}