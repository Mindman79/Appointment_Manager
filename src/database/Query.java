package database;

import java.sql.ResultSet;
import java.sql.Statement;

import static database.DBConnection.*;

public class Query {

    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    public static void queryExecutor() {


        //Customer queries

        try {


            //INSERT data
            //String sqlStatement = "insert into customer(customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES...";

            //Execute INSERT statement
            //stmt.executeUpdate(sqlStatement);

            //DELETE statement
            //String sqlStatement = "DELETE FROM customer WHERE customerID = ...";

            //Write SQL statement
            String sqlStatement = "SELECT * FROM customer";
            QueryManager.makeQuery(sqlStatement);

            //Create ResultSet
            ResultSet result = QueryManager.getResult();


            //Retrieve all records from ResultSet
            /*while(result.next()) {

                System.out.print(result.getInt("customerID") + ", ");
                System.out.print(result.getString("customerName") + ", ");
                System.out.print(result.getString("addressId") + ", ");
                System.out.print(result.getInt("active") + ", ");
                System.out.print(result.getDate("createDate") + ", ");
                System.out.print(result.getTimestamp("lastUpdate") + ", ");
                System.out.print(result.getString("lastUpdateBy"));
                System.out.println();



            }*/





        } catch (Exception e) {
            e.printStackTrace();
        }



    }





}
