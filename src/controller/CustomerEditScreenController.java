package controller;


import java.net.URL;
import java.util.ResourceBundle;

import database.AddressDao;
import database.CityDao;
import database.CountryDao;
import entity.Address;
import entity.City;
import entity.Country;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

    public static Customer selectedCust;

    private Address custAddress;
    private City custCity;
    private Country custCountry;


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



        custAddress = AddressDao.getAddressById(selectedCust.getAddressId());
        custCity = CityDao.getCityById(custAddress.getCityId());
        custCountry = CountryDao.getCountryById(custCity.getCountryId());

        System.out.println(selectedCust.getAddressId());
        System.out.println(custAddress.getCityId());
        System.out.println(custCity.getCountryId());


        customer_name_field.setText(String.valueOf(selectedCust.getCustomerName()));
        address_line_1_field.setText(String.valueOf(custAddress.getAddress1()));
        address_line_2_field.setText(String.valueOf(custAddress.getAddress2()));
        phone_number_field.setText(String.valueOf(custAddress.getPhone()));
        postal_code_field.setText(String.valueOf(custAddress.getPostalCode()));
        city_field.setText(String.valueOf(custCity.getCity()));
        //country_combo_box.setValue(custCountry);

        getCountryNameFromID(custCountry.getCountryId());



    }


    public void getCountryNameFromID(int countryId) {

        String country1 = "USA";
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


    private int setCountryNameFromId(int countryId) {

        String value = (String) country_combo_box.getValue();

        return countryId;
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
