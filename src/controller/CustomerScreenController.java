package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;



public class CustomerScreenController {




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cust_search_button;

    @FXML
    private TextField cust_search_field;

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
    private Button appt_add_button;

    @FXML
    private Button appt_modify_button;

    @FXML
    private Button appt_delete_button;

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
        cust_address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        cust_zip_col.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cust_city_col.setCellValueFactory(new PropertyValueFactory<>("city"));
        cust_phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));





    }










    @FXML
    void AddPartButtonHandler(ActionEvent event) {

    }

    @FXML
    void AppointmentsButtonClickHandler(ActionEvent event) {

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
