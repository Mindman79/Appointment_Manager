package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static controller.LoginScreenController.currentUser;

public class AppointmentEditScreenController {

    private static ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

    private Appointment appointment = new Appointment();

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
    void cancel_button_handler(ActionEvent event) {

    }

    @FXML
    void save_button_handler(ActionEvent event) throws SQLException, IOException {


        //Get start date
        LocalDate startDate = start_date_selector.getValue();
        LocalTime startTime = LocalTime.parse(start_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a"));
        ZonedDateTime start = ZonedDateTime.of(startDate, startTime, localZoneId);

        //Get end date
        LocalDate endDate = end_date_selector.getValue();
        LocalTime endTime = LocalTime.parse(end_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a"));
        ZonedDateTime end = ZonedDateTime.of(endDate, endTime, localZoneId);

//        LocalDateTime end = LocalDateTime.of(end_date_selector.getValue(), LocalTime.parse(end_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a")));

        appointment.setCustomerId(customer_combo_box.getValue().getCustomerId());
        appointment.setAppointmentId(appointment.getAppointmentId());
        //appointment.setUserId(currentUser.getUserId());
        appointment.setTitle(title_field.getText());
        appointment.setDescription(description_field.getText());
        appointment.setLocation(location_field.getText());
        appointment.setContact(contact_field.getText());
        appointment.setType(type_field.getText());
        appointment.setUrl(url_field.getText());
        appointment.setStart(start);
        appointment.setEnd(end);

        //System.out.println("Customer ID test: " + customer_combo_box.getValue().getCustomerId());
        AppointmentDao.updateAppointment(appointment);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


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




    public void receiveAppointment(Appointment selectedAppt) {


        appointment = selectedAppt;
        
        
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



    }
}
