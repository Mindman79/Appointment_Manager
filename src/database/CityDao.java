package database;

import entity.City;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao {

    private static ObservableList<City> City = FXCollections.observableArrayList();

    public static void getCityById(int id)  {


        try {
            String sqlStatement = "SELECT city FROM city WHERE cityId = " + id + " ";

            QueryManager.makeQuery(sqlStatement);

            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                String city = result.getString("city");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }




}
