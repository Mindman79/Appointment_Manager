package database;

import entity.City;
import entity.Country;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    private static ObservableList<Customer> Customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getAllCustomers() {

        //ObservableList<Customer> Custz = FXCollections.observableArrayList();


        Customers.clear();


        try {
            //String sqlStatement = "SELECT * FROM customer";

            String sqlStatement = "SELECT customerId, customerName, address.address, address.phone, address.postalCode, city.city FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId";


            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while(result.next()) {








                Customer customer = new Customer(
                        result.getInt("customerId"),
                        result.getString("customerName")
                        //result.getString("cityName")



                );

                Customers.addAll(customer);





            }

            return Customers;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

}
