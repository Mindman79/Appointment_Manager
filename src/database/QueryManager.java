package database;

import java.sql.ResultSet;
import java.sql.Statement;

import static database.DBConnection.conn;

public class QueryManager {

    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    public static void makeQuery(String q) {

        query = q;

        try {

            //Create statement object
            stmt = conn.createStatement();

            //Determine query execution
            if(query.toLowerCase().startsWith("select")) {

                result = stmt.executeQuery(query);

            }

            if(query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update")) {

                stmt.executeUpdate(query);
            }


        } catch (Exception e) {
            e.printStackTrace();

            System.out.println(e.getMessage());
        }

    }


    public static ResultSet getResult() {

        return result;
    }


}
