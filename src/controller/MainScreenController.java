package controller;

import java.awt.*;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

import database.AddressDao;
import database.AppointmentDao;
import database.CustomerDao;
import entity.Appointment;
import entity.Customer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DateTime;

public class MainScreenController {


    Stage stage;
    Parent scene;

    private static ZoneId localZoneId = ZoneId.systemDefault();

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
    private Button customers_button;

    @FXML
    private Button reports_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button logs_button;

    @FXML
    private ToggleGroup appt_toggle_grp;

    @FXML
    private RadioButton appt_weekly_button;

    @FXML
    private RadioButton appt_monthly_button;

    @FXML
    private RadioButton appt_all_button;


    @FXML
    void appt_monthly_button_handler(ActionEvent event) {

        AppointmentTable.setItems(AppointmentDao.getMonthlyAppointments());
        appt_title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        appt_description_col.setCellValueFactory(new PropertyValueFactory<>("description"));


        //Lambda to insert/format cell data
        appt_start_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().toLocalDateTime().atZone(localZoneId).format(DTformatter)));
        appt_end_col.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getEnd().toLocalDateTime().format(DTformatter)));

    }


    @FXML
    void appt_weekly_button_handler(ActionEvent event) {

        AppointmentTable.setItems(AppointmentDao.getWeeklyAppointments());
        appt_title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        appt_description_col.setCellValueFactory(new PropertyValueFactory<>("description"));



        //Lambda to insert/format cell data
        appt_start_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().toLocalDateTime().atZone(localZoneId).format(DTformatter)));
        appt_end_col.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getEnd().toLocalDateTime().format(DTformatter)));

        //appt_end_col.setCellValueFactory(new PropertyValueFactory<>("end"));


    }

    @FXML
    void appt_all_button_handler(ActionEvent event) {

        initialize();

    }


    @FXML
    void add_button_handler(ActionEvent event) throws IOException {


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentAddScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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

        Appointment selectedAppt = AppointmentTable.getSelectionModel().getSelectedItem();
        AppointmentDao.deleteAppointment(selectedAppt);


        initialize();

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
    void modify_button_handler(ActionEvent event) throws IOException {


        FXMLLoader modifyAppointmentLoader = new FXMLLoader();
        modifyAppointmentLoader.setLocation(getClass().getResource("/view/AppointmentEditScreen.fxml"));
        modifyAppointmentLoader.load();

        //Get another controller
        AppointmentEditScreenController Controller = modifyAppointmentLoader.getController();

        //Connect to receive method in other controller
        Appointment selectedAppt = AppointmentTable.getSelectionModel().getSelectedItem();
        Controller.receiveAppointment(selectedAppt);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = modifyAppointmentLoader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
        
        
    }

    @FXML
    void reports_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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


//        //Lambda to insert/format cell data
       appt_start_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStart().toLocalDateTime().atZone(localZoneId).format(DTformatter)));
        appt_end_col.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getEnd().toLocalDateTime().atZone(localZoneId).format(DTformatter)));

    }


}
