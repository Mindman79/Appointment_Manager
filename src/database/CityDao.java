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

public class CityDao {

    private static ObservableList<City> City = FXCollections.observableArrayList();


    public static City getCityById(int id)  {

        String sqlStatement = "SELECT * FROM city WHERE cityId = " + id + " ";

        System.out.println(id);
        City city = new City();

        try {

            QueryManager.makeQuery(sqlStatement);

            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                city.setCity(result.getString("city"));
                city.setCountryId(result.getInt("countryId"));
                city.setCityId(result.getInt("cityId"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return city;
    }


    public static void updateCity(City city) {

        String updateCity = String.join(" ",
                "UPDATE city",
                "SET city=?, countryId=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE cityId=?");
        try {
            PreparedStatement statement = conn.prepareStatement(updateCity);
            statement.setString(1, city.getCity());
            statement.setInt(2, city.getCountryId());
            statement.setString(3, currentUser.getUserName());
            statement.setInt(4, city.getCityId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }


    public static City addCity(City city) throws SQLException {

        //int nextCityID = getNextCityId();

        String addCity = String.join(" ",
                "INSERT INTO city (cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)",
                "VALUES (?, ?, ?, NOW(), ?, NOW(), ?)");

        try {
            PreparedStatement statement = conn.prepareStatement(addCity);
            statement.setInt(1, city.getCityId());
            statement.setString(2, city.getCity());
            statement.setInt(3, city.getCountryId());
            statement.setString(4, currentUser.getUserName());
            statement.setString(5, currentUser.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return city;

    }

    public static int getNextCityId() throws SQLException {


        int nextCityID = 0;

        String sqlStatement = "SELECT COUNT(*) FROM city";

        try {
            QueryManager.makeQuery(sqlStatement);

            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                nextCityID = result.getInt(1);


            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());

        }

        return nextCityID + 1;

    }
    
    
}
