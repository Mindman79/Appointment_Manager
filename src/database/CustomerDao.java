package database;

import entity.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    private static ObservableList<Customer> Customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getAllCustomers() {


        Customers.clear();


        try {


            String sqlStatement = "SELECT customerId, customerName, address.address, address.phone, address.postalCode, city.city FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId";


            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {

//                City City = new City();
//
//                City.setCityName(result.getString("city"));

                String customerName = result.getString("customerName");
//                String phone = result.getString("phone");
////                //String address = result.getString("address");
////
////                String postalCode = result.getString("postalCode");
////
////                ;

                //City city = new City();
                Customer customer = new Customer(customerName);


/*
                TableRow tr = new TableRow(new ReadOnlyStringWrapper(customerName),
                        new ReadOnlyStringWrapper(phone),
                        new ReadOnlyStringWrapper(address),
                        new ReadOnlyStringWrapper(city),
                        new ReadOnlyStringWrapper(postalCode)*/







/*
                Customer customer = new Customer(
                        result.getInt("customerId"),
                        result.getString("customerName")
                        //result.getString("cityName")*/


                Customers.add(customer);


            }

            return Customers;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }


    public static void updateCustomer(Customer customer) {

        int customerId = customer.getCustomerId();
        //int addressId = customer.getAddressId();
        String customerName = customer.getCustomerName();


        String sqlStatement = "UPDATE customer" +
        "SET customerName=" + customerName + " " +
                "WHERE customerId=" + customerId + "";


        QueryManager.makeQuery(sqlStatement);


    }


}
