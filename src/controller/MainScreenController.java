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
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class MainScreenController {

    Stage stage;
    Parent scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search_appointments_button;

    @FXML
    private TextField search_appointments_field;

    @FXML
    private TableView<?> CustomerTable;

    @FXML
    private TableColumn<?, ?> cust_name_col;

    @FXML
    private TableColumn<?, ?> cust_phone_col;

    @FXML
    private TableColumn<?, ?> cust_address_col;

    @FXML
    private TableColumn<?, ?> cust_city_col;

    @FXML
    private TableColumn<?, ?> cust_zip_col;

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
    void add_button_handler(ActionEvent event) {

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

    }


}
