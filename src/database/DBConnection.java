package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DATABASENAME = "U04Are";
    private static final String DB_URL = "jdbc:mysql://3.227.166.251/" + DATABASENAME;
    private static final String USERNAME = "U04Are";
    private static final String PASSWORD = "53688183635";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    static Connection conn;

    public static void makeConnection() throws ClassNotFoundException, SQLException, Exception {

        Class.forName(DRIVER);
        conn = (Connection) DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        System.out.println("Connection successful");
    }


    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception {

        conn.close();
        System.out.println("Connection closed");
    }


}
