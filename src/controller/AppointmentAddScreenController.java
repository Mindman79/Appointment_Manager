package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utils.DateTime;

import static controller.LoginScreenController.currentUser;

public class AppointmentAddScreenController {

    Stage stage;
    Parent scene;

    Appointment appointment = new Appointment();

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
    private RadioButton EndTimeAMRadioButton;

    @FXML
    private ToggleGroup EndTimeToggle;

    @FXML
    private RadioButton EndTimePMRadioButton;

    @FXML
    private Button save_button;

    @FXML
    private Button cancel_button;

    @FXML
    private RadioButton StartTimeAMRadioButton;

    @FXML
    private RadioButton StartTimePMRadioButton;

    @FXML
    private ToggleGroup StartTimeToggle;

    @FXML
    void EndTimeAMRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void EndTimePMRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void StartTimeAMRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void cancel_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void save_button_handler(ActionEvent event) throws SQLException, IOException {


        LocalDateTime start = LocalDateTime.of(start_date_selector.getValue(), LocalTime.parse(start_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a")));

        LocalDateTime end = LocalDateTime.of(end_date_selector.getValue(), LocalTime.parse(end_time_field.getText(), DateTimeFormatter.ofPattern("hh:mm a")));

        appointment.setCustomerId(customer_combo_box.getValue().getCustomerId());
        //appointment.setUserId(currentUser.getUserId());
        appointment.setTitle(title_field.getText());
        appointment.setDescription(description_field.getText());
        appointment.setLocation(location_field.getText());
        appointment.setContact(contact_field.getText());
        appointment.setType(type_field.getText());
        appointment.setUrl(url_field.getText());
        appointment.setStart(start);
        appointment.setEnd(end);

        AppointmentDao.addAppointment(appointment);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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
    }
}
