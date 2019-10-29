package controller;


import java.net.URL;
import java.util.ResourceBundle;

import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CustomerEditScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField customer_name_field;

    @FXML
    private TextField address_line_1_field;

    @FXML
    private TextField city_field;

    @FXML
    private TextField postal_code_field;

    @FXML
    private TextField phone_number_field;

    @FXML
    private ComboBox<?> country_combo_box;

    @FXML
    private TextField address_line_2_field;

    @FXML
    private Button save_button;

    @FXML
    private Button cancel_button;

    @FXML
    void cancel_button_handler(ActionEvent event) {

    }

    @FXML
    void save_button_handler(ActionEvent event) {

    }

    @FXML
    void initialize() {




    }

    public void receiveCustomer(Customer selectedItem) {

        customer_name_field.setText(String.valueOf(selectedItem.getCustomerName()));

    }
}
