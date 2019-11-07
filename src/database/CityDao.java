package database;

import entity.Address;
import entity.City;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                "SET city=?, countryId=?, lastUpdate=NOW()",
                "WHERE cityId=?");
        try {
            PreparedStatement statement = conn.prepareStatement(updateCity);
            statement.setString(1, city.getCity());
            statement.setInt(2, city.getCountryId());
            System.out.println("Country ID test 5: " + city.getCountryId());
            //statement.setString(3, loggedUser.getUserName());
            statement.setInt(3, city.getCityId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }


}
