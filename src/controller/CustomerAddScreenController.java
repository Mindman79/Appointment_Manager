package controller;


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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAddScreenController {

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

    private Customer newCustomer = new Customer();
    private Address custAddress = new Address();
    private City custCity = new City();
    private Country custCountry = new Country();
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

        int customerId = newCustomer.getCustomerId();

        System.out.println("Country ID last test: " + countryId);

        //Customer
        newCustomer.setCustomerName(customer_name_field.getText());
        newCustomer.setCustomerId(customerId);



        //Address
        custAddress.setAddress1(address_line_1_field.getText());
        custAddress.setAddress2(address_line_2_field.getText());
        custAddress.setPostalCode(postal_code_field.getText());
        custAddress.setPhone(phone_number_field.getText());

        //City
        custCity.setCity(city_field.getText());
        custCity.setCountryId(countryId);



        CustomerDao.addCustomer(newCustomer);
        AddressDao.updateAddress(custAddress);
        CityDao.updateCity(custCity);
        CountryDao.updateCountry(custCountry);


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("Save button customerID test: " + newCustomer.getCustomerId());

        System.out.println("Save button addressID test: " + custAddress.getAddressId());

        System.out.println("Save button cityID test: " + custCity.getCityId());

        System.out.println("Save button countryID test: " + custCountry.getCountryId());


    }

    @FXML
    void initialize() {


    }





    public void getCountryNameFromID(int countryId) {

        String country1 = "US";
        String country2 = "Canada";
        String country3 = "Norway";

        country_combo_box.getItems().add(country1);
        country_combo_box.getItems().add(country2);
        country_combo_box.getItems().add(country3);


        System.out.println("Country ID selected: " + countryId);

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




//    public void convertCountryToString() {
//
//        custCountry.setConverter(new StringConverter<Country>() {
//            @Override
//            public String toString(Country country) {
//                return country.getCountry();
//            }
//
//            @Override
//            public Country fromString(String string) {
//                return custCountry.getValue();
//            }
//        });
//    }


}
