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

    private static ObservableList<Appointment> Appointments = FXCollections.observableArrayList();


    public static ObservableList<Appointment> getAllAppointments() {


        Appointments.clear();


        try {


            /* String sqlStatement = "SELECT customerId, customerName, address.address, address.phone, address.postalCode, city.city FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId";*/


            String sqlStatement = "SELECT * FROM appointment";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {


                String appointmentTitle = result.getString("title");
                String appointmentDescription = result.getString("description");
                String appointmentLocation = result.getString("location");
                LocalDateTime appointmentStartTime = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime appointmentEndTime = result.getTimestamp("end").toLocalDateTime();
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


            String sqlStatement = "select * from appointment where start between utc_date + 0  and utc_date + 7" +
                    "order by start desc;";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {


                String appointmentTitle = result.getString("title");
                String appointmentDescription = result.getString("description");
                String appointmentLocation = result.getString("location");
                LocalDateTime appointmentStartTime = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime appointmentEndTime = result.getTimestamp("end").toLocalDateTime();
                Appointment appointment = new Appointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentStartTime, appointmentEndTime);




                Appointments.add(appointment);

            }

            return Appointments;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public static Appointment dateTimeConverter(Appointment appointment) {

        //Get local Time Zone ID
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        //Get startDateTime
        LocalDateTime appointmentStartDateTime = appointment.getStart();
        //System.out.println("Appointment start date/time non-UTC: " + appointmentStartDateTime);
        ZonedDateTime startLDT = ZonedDateTime.of(appointmentStartDateTime, localZoneId);


        //Get endDateTime
        LocalDateTime appointmentEndDateTime = appointment.getEnd();
        ZonedDateTime endLDT = ZonedDateTime.of(appointmentEndDateTime, localZoneId);

        Instant startTimeToUTC = startLDT.toInstant();
        Instant endTimeToUTC = endLDT.toInstant();
        Timestamp timestampStart = Timestamp.from(startTimeToUTC);
        Timestamp timestampEnd = Timestamp.from(startTimeToUTC);


        return appointment;
    }


    public static void addAppointment(Appointment appointment) throws SQLException {

        //Get local Time Zone ID
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        //Get startDateTime
        LocalDateTime appointmentStartDateTime = appointment.getStart();
        System.out.println("Appointment start date/time non-UTC: " + appointmentStartDateTime);
        ZonedDateTime startLDT = ZonedDateTime.of(appointmentStartDateTime, localZoneId);


        //Get endDateTime
        LocalDateTime appointmentEndDateTime = appointment.getEnd();
        ZonedDateTime endLDT = ZonedDateTime.of(appointmentEndDateTime, localZoneId);


        //LocalTime localTime = LocalTime.now();
        //LocalDateTime localDate = LocalDateTime.now();




        Instant startTimeToUTC = startLDT.toInstant();
        Instant endTimeToUTC = endLDT.toInstant();
        Timestamp timestampStart = Timestamp.from(startTimeToUTC);
        Timestamp timestampEnd = Timestamp.from(endTimeToUTC);

//        System.out.println("Start time UTC: " + startTimeToUTC.truncatedTo(ChronoUnit.SECONDS));
//        System.out.println("End time UTC " + endTimeToUTC.truncatedTo(ChronoUnit.MINUTES));

//        String datetime = String.valueOf(startTimeToUTC);
//        datetime = datetime.substring(0, datetime.length() - 4);
//        System.out.println("date time test: " + datetime);

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm AA"); // your template here
//        java.util.Date dateStr = formatter.parse(startTimeToUTC);

//
//        String fullPattern = "MM/dd/yyyy HH:mm AA";
//        SimpleDateFormat fullDateFormat = new SimpleDateFormat(startTimeToUTC);


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
            System.out.println("User ID test: " + currentUser.getUserId());
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
