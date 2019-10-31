package database;

import entity.Address;
import entity.City;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao {

    private static ObservableList<Address> Addresses = FXCollections.observableArrayList();
    private static Address addresses;

    //public Address address;

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

}
