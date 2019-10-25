package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainScreenController {

    Stage stage;
    Parent scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button appt_search_button;

    @FXML
    private TextField appt_search_field;

    @FXML
    private TableView<?> AppointmentTable;

    @FXML
    private TableColumn<?, ?> appt_description_col;

    @FXML
    private TableColumn<?, ?> appt_contact_col;

    @FXML
    private TableColumn<?, ?> appt_location_col;

    @FXML
    private TableColumn<?, ?> appt_start_date_col;

    @FXML
    private TableColumn<?, ?> appt_end_date_col;

    @FXML
    private Button appt_add_button;

    @FXML
    private Button appt_modify_button;

    @FXML
    private Button appt_delete_button;

    @FXML
    private Button customers_button;

    @FXML
    private Button reports_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button logs_button;

    @FXML
    void AddPartButtonHandler(ActionEvent event) {

    }

    @FXML
    void CustomersButtonClickHandler(ActionEvent event) throws IOException {


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void DeletePartButtonHandler(ActionEvent event) {

    }

    @FXML
    void ExitButtonHandler(ActionEvent event) {

    }

    @FXML
    void LogsButtonClickHandler(ActionEvent event) {

    }

    @FXML
    void ModifyPartButtonHandler(ActionEvent event) {

    }

    @FXML
    void ReportsButtonClickHandler(ActionEvent event) {

    }

    @FXML
    void SearchPartsButtonHandler(KeyEvent event) {

    }

    @FXML
    void SearchPartsFieldHandler(ActionEvent event) {

    }


}
