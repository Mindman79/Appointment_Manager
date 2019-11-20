package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.AddressDao;
import database.AppointmentDao;
import database.CustomerDao;
import entity.Appointment;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DateTime;

public class MainScreenController {


    Stage stage;
    Parent scene;

    private final DateTimeFormatter DTformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search_appointments_button;

    @FXML
    private TextField search_appointments_field;

    @FXML
    private TableView<Appointment> AppointmentTable;

    @FXML
    private TableColumn<Appointment, String> appt_title_col;

    @FXML
    private TableColumn<Appointment, String> appt_description_col;

    @FXML
    private TableColumn<Appointment, String> appt_location_col;

    @FXML
    private TableColumn<Appointment, String> appt_start_col;

    @FXML
    private TableColumn<Appointment, String> appt_end_col;

    @FXML
    private Button add_button;

    @FXML
    private Button modify_button;

    @FXML
    private Button delete_button;

    @FXML
    private ToggleButton appointments_toggle_button;

    @FXML
    private Button customers_button;

    @FXML
    private Button reports_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button logs_button;

    @FXML
    void add_button_handler(ActionEvent event) throws IOException {


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentAddScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void appointments_toggle_button_handler(ActionEvent event) {

    }

    @FXML
    void customers_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void delete_button_handler(ActionEvent event) {

    }

    @FXML
    void exit_button_handler(ActionEvent event) {

    }

    @FXML
    void logs_button_handler(ActionEvent event) {

    }

    @FXML
    void modify_button_handler(ActionEvent event) {

    }

    @FXML
    void reports_button_handler(ActionEvent event) {

    }

    @FXML
    void search_appointments_button_handler(ActionEvent event) {

    }

    @FXML
    void search_appointments_field_handler(ActionEvent event) {

    }




    @FXML
    void initialize() {

        AppointmentTable.setItems(AppointmentDao.getAllAppointments());
        appt_title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        appt_description_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        appt_location_col.setCellValueFactory(new PropertyValueFactory<>("location"));


        //Lambda to insert/format cell data
        appt_start_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().format(DTformatter)));
        appt_end_col.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getEnd().format(DTformatter)));



    }


}
