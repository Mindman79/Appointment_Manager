package controller;

import database.CustomerDao;
import entity.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


public class CustomerScreenController {


    Stage stage;
    Parent scene;



    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TableColumn<Customer, String> cust_name_col;

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


        CustomerTable.setItems(CustomerDao.getAllCustomers());
        cust_name_col.setCellValueFactory(new PropertyValueFactory<>("customerName"));



    }


    @FXML
    void add_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerAddScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void appointments_button_handler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void delete_button_handler(ActionEvent event) {

        Customer selectedCust = CustomerTable.getSelectionModel().getSelectedItem();
        CustomerDao.deleteCustomer(selectedCust);

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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void modify_button_handler(ActionEvent event) throws IOException {

        FXMLLoader modifyCustomerLoader = new FXMLLoader();
        modifyCustomerLoader.setLocation(getClass().getResource("/view/CustomerEditScreen.fxml"));
        modifyCustomerLoader.load();

        //Get another controller
        CustomerEditScreenController Controller = modifyCustomerLoader.getController();

        //Connect to receive method in other controller
        Customer selectedCust = CustomerTable.getSelectionModel().getSelectedItem();

        Controller.receiveCustomer(selectedCust);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = modifyCustomerLoader.getRoot();
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



}
