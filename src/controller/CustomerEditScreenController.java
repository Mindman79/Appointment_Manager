package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.AddressDao;
import database.CityDao;
import database.CountryDao;
import database.CustomerDao;
import entity.Address;
import entity.City;
import entity.Country;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
    private ComboBox<String> country_combo_box;

    @FXML
    private TextField address_line_2_field;

    @FXML
    private Button save_button;

    @FXML
    private Button cancel_button;

    private Customer selectedCustomer;
    private Address custAddress;
    private City custCity;
    private Country custCountry;
    private int countryId;


    Stage stage;
    Parent scene;

    @FXML
    void cancel_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void save_button_handler(ActionEvent event) throws IOException {


        setCountryIDFromName();

        int customerId = selectedCustomer.getCustomerId();


        //Customer
        selectedCustomer.setCustomerName(customer_name_field.getText());
        selectedCustomer.setCustomerId(customerId);



        //Address
        custAddress.setAddress1(address_line_1_field.getText());
        custAddress.setAddress2(address_line_2_field.getText());
        custAddress.setPostalCode(postal_code_field.getText());
        custAddress.setPhone(phone_number_field.getText());

        //City
        custCity.setCity(city_field.getText());
        custCity.setCountryId(countryId);


        //Customer input validation. Country selection does not validate here, as one must already be selected.
        if(customer_name_field.getText().trim().isEmpty()) {

            alertGenerator("Customer name cannot be blank!");

        } else if(address_line_1_field.getText().trim().isEmpty()){

            alertGenerator("Address (first line) cannot be blank!");

        } else if(city_field.getText().trim().isEmpty()){

            alertGenerator("City name cannot be blank!");

        } else if(postal_code_field.getText().trim().isEmpty()){

            alertGenerator("Postal code cannot be blank!");

        } else if(phone_number_field.getText().trim().isEmpty()){

            alertGenerator("Phone number cannot be blank!");

        } else {

            CustomerDao.updateCustomer(selectedCustomer);
            AddressDao.updateAddress(custAddress);
            CityDao.updateCity(custCity);
            CountryDao.updateCountry(custCountry);


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }


    }

    @FXML
    void initialize() {


    }



    public void receiveCustomer(Customer selectedCust) {


        selectedCustomer = selectedCust;
        custAddress = AddressDao.getAddressById(selectedCustomer.getAddressId());
        custCity = CityDao.getCityById(custAddress.getCityId());
        custCountry = CountryDao.getCountryById(custCity.getCountryId());



        customer_name_field.setText(String.valueOf(selectedCustomer.getCustomerName()));
        address_line_1_field.setText(String.valueOf(custAddress.getAddress1()));
        address_line_2_field.setText(String.valueOf(custAddress.getAddress2()));
        phone_number_field.setText(String.valueOf(custAddress.getPhone()));
        postal_code_field.setText(String.valueOf(custAddress.getPostalCode()));
        city_field.setText(String.valueOf(custCity.getCity()));
        getCountryNameFromID(custCountry.getCountryId());



    }


    public void getCountryNameFromID(int countryId) {

        String country1 = "US";
        String country2 = "Canada";
        String country3 = "Norway";

        country_combo_box.getItems().add(country1);
        country_combo_box.getItems().add(country2);
        country_combo_box.getItems().add(country3);


        if(countryId == 1) {
            country_combo_box.setValue(country1);

        } else if(countryId == 2) {
            country_combo_box.setValue(country2);

        } else if(countryId == 3) {
            country_combo_box.setValue(country3);

        }


    }


    private void setCountryIDFromName() {

        String value = (String) country_combo_box.getValue();



        if(value.contains("US")) {
            countryId = 1;

        } else if(value.contains("Canada")) {
            countryId = 2;

        } else if(value.contains("Norway")) {
            countryId = 3;

        }



    }


    public void alertGenerator(String message) {

        Alert apptAlert = new Alert(Alert.AlertType.INFORMATION);
        apptAlert.setTitle(message);
        apptAlert.setHeaderText(message);
        apptAlert.showAndWait();


    }

}
