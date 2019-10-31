package controller;


import java.net.URL;
import java.util.ResourceBundle;

import database.AddressDao;
import database.CityDao;
import entity.Address;
import entity.City;
import entity.Country;
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

    public static Customer selectedCust;

    private Address custAddress;
    //private City custCity = CityDao.getCityById(custAddress.getCityId());
    //private Country custCountry = DBCountry.getCountryById(custCity.getCountryId());



    @FXML
    void cancel_button_handler(ActionEvent event) {

    }

    @FXML
    void save_button_handler(ActionEvent event) {

    }

    @FXML
    void initialize() {



    }

    public void receiveCustomer(Customer selectedCust) {


        System.out.println(selectedCust.getAddressId());
       custAddress = AddressDao.getAddressById(selectedCust.getAddressId());



        customer_name_field.setText(String.valueOf(selectedCust.getCustomerName()));
        address_line_1_field.setText(String.valueOf(custAddress.getAddress1()));
        address_line_2_field.setText(String.valueOf(custAddress.getAddress2()));
        phone_number_field.setText(String.valueOf(custAddress.getPhone()));
        postal_code_field.setText(String.valueOf(custAddress.getPostalCode()));

    }
}
