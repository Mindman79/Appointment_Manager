package database;

import entity.Address;
import entity.Country;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DBConnection.conn;

public class CountryDao {

    private static ObservableList<Country> country = FXCollections.observableArrayList();


    public static Country getCountryById(int id)  {

        String sqlStatement = "SELECT * FROM country WHERE countryId = " + id + " ";

        System.out.println(id);
        Country country = new Country();

        try {

            QueryManager.makeQuery(sqlStatement);

            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                country.setCountry(result.getString("country"));
                country.setCountryId(result.getInt("countryId"));
            }

            System.out.println("Country ID: " );

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return country;
    }

    public static void updateCountry(Country country) {

        //TODO : switch country and countryID here

        String updateCountry = String.join(" ",
                "UPDATE country",
                "SET country=?, lastUpdate=NOW()",
                "WHERE countryId=?");
        try {
            PreparedStatement statement = conn.prepareStatement(updateCountry);
            statement.setString(1, country.getCountry());
            //statement.setString(2, loggedUser.getUserName());
            statement.setInt(2, country.getCountryId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

}
