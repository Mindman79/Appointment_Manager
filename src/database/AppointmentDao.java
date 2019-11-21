package database;

import entity.Address;
import entity.Appointment;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DateTime;

import javax.xml.bind.SchemaOutputResolver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import static controller.LoginScreenController.currentUser;
import static database.DBConnection.conn;

public class AppointmentDao {

    private static ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

    private static ObservableList<Appointment> Appointments = FXCollections.observableArrayList();


    public static ObservableList<Appointment> getAllAppointments() {



        System.out.println("Local Zone ID: " + localZoneId);
        //ZoneOffset offset = ZoneId.ofOffset(lo);
        Appointments.clear();


        try {


            /* String sqlStatement = "SELECT customerId, customerName, address.address, address.phone, address.postalCode, city.city FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId";*/


            String sqlStatement = "SELECT * FROM appointment ORDER BY start ASC";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {


                String appointmentTitle = result.getString("title");
                String appointmentDescription = result.getString("description");
                String appointmentLocation = result.getString("location");
                ZonedDateTime appointmentStartTime = result.getTimestamp("start").toLocalDateTime().atZone(localZoneId);
                ZonedDateTime appointmentEndTime = result.getTimestamp("end").toLocalDateTime().atZone(localZoneId);
                Appointment appointment = new Appointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentStartTime, appointmentEndTime);


                Appointments.add(appointment);

            }

            return Appointments;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }


    public static ObservableList<Appointment> getWeeklyAppointments() {


        Appointments.clear();


        try {


            String sqlStatement = "select * from appointment where start between utc_date + 0  and utc_date + 6 ORDER BY start ASC";
                    //"order by start desc;";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {


                String appointmentTitle = result.getString("title");
                String appointmentDescription = result.getString("description");
                String appointmentLocation = result.getString("location");
                ZonedDateTime appointmentStartTime = result.getTimestamp("start").toLocalDateTime().atZone(localZoneId);
                ZonedDateTime appointmentEndTime = result.getTimestamp("end").toLocalDateTime().atZone(localZoneId);
                Appointment appointment = new Appointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentStartTime, appointmentEndTime);




                Appointments.add(appointment);

            }

            return Appointments;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public static Timestamp startDateTimeConverter(Appointment appointment) {



        ZonedDateTime appointmentStartDateTime = appointment.getStart();
        ZonedDateTime startLDT = appointmentStartDateTime;

        Instant startTimeToUTC = startLDT.toInstant();
        Timestamp timestampStart = Timestamp.from(startTimeToUTC);

        return timestampStart;

    }


    public static Timestamp endDateTimeConverter(Appointment appointment) {


        ZonedDateTime appointmentEndDateTime = appointment.getEnd();
        ZonedDateTime endLDT = appointmentEndDateTime;

        Instant endTimeToUTC = endLDT.toInstant();
        System.out.println("Timestamp UTC: " + endTimeToUTC);

        Timestamp timestampEnd = Timestamp.from(endTimeToUTC);
        System.out.println("Timestamp end: " + timestampEnd);

        return timestampEnd;

    }


    public static void addAppointment(Appointment appointment) throws SQLException {




        int appointmentId = getNextApptId();

        Timestamp timestampStart = startDateTimeConverter(appointment);
        Timestamp timestampEnd = endDateTimeConverter(appointment);

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
            statement.setObject(10, timestampStart);
            statement.setObject(11, timestampEnd);
            statement.setObject(12, LocalDate.now());
            statement.setString(13, currentUser.getUserName());
            statement.setString(14, currentUser.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
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
