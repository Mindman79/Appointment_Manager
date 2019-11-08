package database;

import entity.Address;
import entity.City;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static controller.LoginScreenController.currentUser;
import static database.DBConnection.conn;

public class AddressDao {

    private static ObservableList<Address> Addresses = FXCollections.observableArrayList();
    private static Address addresses;



    public static Address getAddressById(int id)  {

        String sqlStatement = "SELECT * FROM address WHERE addressId = " + id + " ";

        System.out.println(id);
        Address address = new Address();

        try {

            QueryManager.makeQuery(sqlStatement);

            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                address.setAddressId(result.getInt("addressId"));
                address.setAddress1(result.getString("address"));
                address.setAddress2(result.getString("address2"));
                address.setCityId(result.getInt("cityId"));
                address.setPostalCode(result.getString("postalCode"));
                address.setPhone(result.getString("phone"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return address;
    }


    public static void updateAddress(Address address) {

        String updateAddress = String.join(" ",
                "UPDATE address",
                "SET address=?, address2=?, cityId=?, postalCode=?, phone=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE addressId=?");

        try {
            PreparedStatement statement = conn.prepareStatement(updateAddress);
            statement.setString(1, address.getAddress1());
            statement.setString(2, address.getAddress2());
            statement.setInt(3, address.getCityId());
            statement.setString(4, address.getPostalCode());
            statement.setString(5, address.getPhone());
            statement.setString(6, currentUser.getUserName());
            statement.setInt(7, address.getAddressId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }


}
