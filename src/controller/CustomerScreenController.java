package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.CustomerDao;
import database.QueryManager;
import entity.Customer;
import entity.TableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class CustomerScreenController {


    Stage stage;
    Parent scene;

    public static Customer selectedCust;

    @FXML
    private Button search_customers_button;

    @FXML
    private TextField search_customers_field;


    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TableColumn<Customer, String> cust_name_col;

    @FXML
    private TableColumn<Customer, String> cust_phone_col;

    @FXML
    private TableColumn<Customer, String> cust_address_col;

    @FXML
    private TableColumn<Customer, String> cust_city_col;

    @FXML
    private TableColumn<Customer, String> cust_zip_col;

    @FXML
    private Button add_button;

    @FXML
    private Button modify_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button appointments_button;

    @FXML
    private Button reports_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button logs_button;




    @FXML
    void initialize() {


        //Must match table name, or constructor name... or something like that
        CustomerTable.setItems(CustomerDao.getAllCustomers());
        cust_name_col.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        /*cust_address_col.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        cust_zip_col.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cust_city_col.setCellValueFactory(new PropertyValueFactory<>("city"));
        cust_phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));*/


    }


    @FXML
    void add_button_handler(ActionEvent event) {

    }

    @FXML
    void appointments_button_handler(ActionEvent event) {

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
    void modify_button_handler(ActionEvent event) throws IOException {

        FXMLLoader modifyCustomerLoader = new FXMLLoader();
        modifyCustomerLoader.setLocation(getClass().getResource("/view/CustomerEditScreen.fxml"));
        modifyCustomerLoader.load();

        //Get another controller
        CustomerEditScreenController Controller = modifyCustomerLoader.getController();

        //Connect to receive method in other controller
        selectedCust = CustomerTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedCust.getAddressId());
        Controller.receiveCustomer(selectedCust);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = modifyCustomerLoader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();





    }

    @FXML
    void reports_button_handler(ActionEvent event) {

    }

    @FXML
    void search_customers_button_handler(ActionEvent event) {

    }

    @FXML
    void search_customers_field_handler(ActionEvent event) {

    }


}
