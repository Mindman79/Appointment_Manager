package database;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import entity.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import static database.DBConnection.conn;

public class CustomerDao {

    private static ObservableList<Customer> Customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getAllCustomers() {


        Customers.clear();


        try {


           /* String sqlStatement = "SELECT customerId, customerName, address.address, address.phone, address.postalCode, city.city FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId";*/


            String sqlStatement = "SELECT * FROM customer";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {



                String customerName = result.getString("customerName");
                int addressId = result.getInt("addressId");

                Customer customer = new Customer(customerName, addressId);


               /* TableRow tr = new TableRow(new ReadOnlyStringWrapper(customerName),
                        new ReadOnlyStringWrapper(phone),
                        new ReadOnlyStringWrapper(address),
                        new ReadOnlyStringWrapper(city),
                        new ReadOnlyStringWrapper(postalCode)*/


                Customers.add(customer);

            }

            return Customers;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }


//    public static void updateCustomer(Customer customer) {
//
//        int customerId = customer.getCustomerId();
//        String customerName = customer.getCustomerName();
//
//        String sqlStatement = "UPDATE customer" +
//                "SET customerName=" + customerName + "customerId" + customerId"addressId=" +  +
//
//                "WHERE customerId=" + customerId + " " +
//                ;
//
//
//
//
//
//
//
//        QueryManager.makeQuery(sqlStatement);
//
//
//    }



    public static void updateCustomer(Customer customer) {

        String updateCustomer = String.join(" ",
                "UPDATE customer",
                "SET customerName=?, addressId=?, lastUpdate=NOW()",
                "WHERE customerId = ?");

        try {
            PreparedStatement statement = conn.prepareStatement(updateCustomer);
            statement.setString(1, customer.getCustomerName());
            statement.setInt(2, customer.getAddressId());
            //statement.setString(3, loggedUser.getUserName());
            statement.setInt(3, customer.getCustomerId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }



}
