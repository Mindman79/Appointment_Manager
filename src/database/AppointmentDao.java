package database;

import entity.Address;
import entity.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import static controller.LoginScreenController.currentUser;
import static database.DBConnection.conn;

public class AppointmentDao {




    public static void addAppointment(Appointment appointment) throws SQLException {


        int appointmentId = getNextApptId();


        String addAppointment = String.join(" ",
                "INSERT INTO appointment (appointmentId, customerId, userId, title, "
                        + "description, location, contact, type, url, start, end, " //11
                        + "createDate, createdBy, lastUpdate, lastUpdateBy) ",
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?)");

        try {
            PreparedStatement statement = conn.prepareStatement(addAppointment);
            statement.setInt(1, appointmentId);
            statement.setObject(2, appointment.getCustomerId());
            statement.setObject(3, currentUser.getUserId());
            statement.setObject(4, appointment.getTitle());
            statement.setObject(5, appointment.getDescription());
            statement.setObject(6, appointment.getLocation());
            statement.setObject(7, appointment.getContact());
            statement.setObject(8, appointment.getType());
            statement.setObject(9, appointment.getUrl());
            statement.setObject(10, appointment.getStart());
            statement.setObject(11, appointment.getEnd());
            statement.setObject(12, LocalDate.now());
            statement.setString(13, currentUser.getUserName());
            statement.setString(14, currentUser.getUserName());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception (address): " + e.getMessage());
        }




    }



    public static int getNextApptId() throws SQLException {


        int nextApptID = 0;

        String sqlStatement = "SELECT COUNT(*) FROM appointment";

        try {
            QueryManager.makeQuery(sqlStatement);

            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                nextApptID = result.getInt(1);


            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());

        }

        return nextApptID + 1;

    }


}
