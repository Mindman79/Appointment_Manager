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

    private static ZoneId localZoneId = ZoneId.systemDefault();

    private static ObservableList<Appointment> Appointments = FXCollections.observableArrayList();


    public static ObservableList<Appointment> getAllAppointments() {

        Appointments.clear();

        try {


            String sqlStatement = "SELECT * FROM appointment ORDER BY start ASC";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                Appointment appointment = new Appointment();
                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setTitle(result.getString("title"));
                appointment.setDescription(result.getString("description"));
                appointment.setLocation(result.getString("location"));
                appointment.setContact(result.getString("contact"));
                appointment.setType(result.getString("type"));
                appointment.setUrl(result.getString("url"));

                LocalDateTime startUTC = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = result.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), localZoneId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), localZoneId);

                appointment.setStart(startLocal);
                appointment.setEnd(endLocal);


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
        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now().plusWeeks(1);

        try {


            String sqlStatement = "select * from appointment where start >= '" + begin + "' and start <= '" + end + "'";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                Appointment appointment = new Appointment();
                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setTitle(result.getString("title"));
                appointment.setDescription(result.getString("description"));
                appointment.setLocation(result.getString("location"));
                appointment.setContact(result.getString("contact"));
                appointment.setType(result.getString("type"));
                appointment.setUrl(result.getString("url"));

                LocalDateTime startUTC = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = result.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), localZoneId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), localZoneId);

                appointment.setStart(startLocal);
                appointment.setEnd(endLocal);


                Appointments.add(appointment);

            }

            return Appointments;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }


    public static ObservableList<Appointment> getMonthlyAppointments() {


        Appointments.clear();
        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now().plusMonths(1);

        try {


            String sqlStatement = "select * from appointment where start >= '" + begin + "' and start <= '" + end + "'";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                Appointment appointment = new Appointment();
                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setTitle(result.getString("title"));
                appointment.setDescription(result.getString("description"));
                appointment.setLocation(result.getString("location"));
                appointment.setContact(result.getString("contact"));
                appointment.setType(result.getString("type"));
                appointment.setUrl(result.getString("url"));

                LocalDateTime startUTC = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = result.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), localZoneId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), localZoneId);

                appointment.setStart(startLocal);
                appointment.setEnd(endLocal);


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

        ZonedDateTime startToUTC = startLDT.withZoneSameInstant(ZoneId.of("UTC"));

        LocalDateTime startTime = startToUTC.toLocalDateTime();
        Timestamp timestampStart = Timestamp.valueOf(startTime);
        System.out.println("Timestamp start: " + timestampStart);

        return timestampStart;

    }


    public static Timestamp endDateTimeConverter(Appointment appointment) {


        ZonedDateTime appointmentEndDateTime = appointment.getEnd();
        ZonedDateTime endLDT = appointmentEndDateTime;

//        Instant endTimeToUTC = endLDT.toInstant();
//        System.out.println("Timestamp UTC: " + endTimeToUTC);

        ZonedDateTime endToUTC = endLDT.withZoneSameInstant(ZoneId.of("UTC"));

        LocalDateTime endTime = endToUTC.toLocalDateTime();
        Timestamp timestampEnd = Timestamp.valueOf(endTime);
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
            System.out.println("SQL Exception (appointment): " + e.getMessage());
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


    public static void deleteAppointment(Appointment appointment) {

        String deleteAppointment = String.join(" ",
                "DELETE appointment FROM appointment WHERE appointmentId = ?");

        try {
            PreparedStatement statement = conn.prepareStatement(deleteAppointment);
            statement.setInt(1, appointment.getAppointmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }


    public static void updateAppointment(Appointment appointment) throws SQLException {


        Timestamp timestampStart = startDateTimeConverter(appointment);
        Timestamp timestampEnd = endDateTimeConverter(appointment);

        String updateAppointment = String.join(" ",
                "UPDATE appointment",
                "SET customerId=?, userId=?, title=?, description=?, location=?," +
                        "contact=?, type=?, url=?, start=?, end=?, lastUpdate=NOW(), lastUpdateBy=?",
                "WHERE appointmentId=?");

        try {
            PreparedStatement statement = conn.prepareStatement(updateAppointment);
            statement.setInt(1, appointment.getCustomerId());
            statement.setInt(2, currentUser.getUserId());
            statement.setObject(3, appointment.getTitle());
            statement.setObject(4, appointment.getDescription());
            statement.setObject(5, appointment.getLocation());
            statement.setObject(6, appointment.getContact());
            statement.setObject(7, appointment.getType());
            statement.setObject(8, appointment.getUrl());
            statement.setTimestamp(9, timestampStart);
            statement.setTimestamp(10, timestampEnd);
            statement.setObject(11, currentUser.getUserName());
            statement.setInt(12, appointment.getAppointmentId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception (appointment): " + e.getMessage());
        }


    }


    public static Appointment getAppointmentById(int id) {


        Appointment appointment = new Appointment();

        try {


            String sqlStatement = "SELECT * FROM appointment WHERE appointmentId = " + id + " ";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {


                //appointment.setCustomerId(result.getInt());
                appointment.setTitle(result.getString("title"));
                appointment.setDescription(result.getString("description"));

                LocalDateTime startUTC = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = result.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), localZoneId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), localZoneId);

                appointment.setStart(startLocal);
                appointment.setEnd(endLocal);


                Appointments.add(appointment);

            }

            return appointment;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }


    public static Appointment getUpcomingAppointments() {


        try {


            Appointment appointment = new Appointment();
            String upcomingAppointment = "SELECT * FROM appointment WHERE (start BETWEEN NOW() AND ADDTIME(NOW(), '00:15:00'))";

//            QueryManager.makeQuery(sqlStatement);
//            ResultSet result = QueryManager.getResult();


            PreparedStatement statement = conn.prepareStatement(upcomingAppointment);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                //Appointment appointment = new Appointment();
                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setTitle(result.getString("title"));
                appointment.setDescription(result.getString("description"));
                appointment.setLocation(result.getString("location"));
                appointment.setContact(result.getString("contact"));
                appointment.setType(result.getString("type"));
                appointment.setUrl(result.getString("url"));

                LocalDateTime startUTC = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = result.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), localZoneId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), localZoneId);

                appointment.setStart(startLocal);
                appointment.setEnd(endLocal);


            }

            return appointment;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        }
    }


    public static Appointment overlappingAppointment(Appointment appointment) {

        Timestamp timestampStart = startDateTimeConverter(appointment);
        Timestamp timestampEnd = endDateTimeConverter(appointment);


        try {


            //String sqlStatement = "select * FROM appointment WHERE (start between '" + timestampStart'" + AND + "'"timestampEnd"''");

            String sqlStatement = "select * from appointment where start between '" + timestampStart + "' and '" + timestampEnd + "'";

            System.out.println("Start time: " + timestampStart);
            System.out.println("End time:" + timestampEnd);


            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet result = statement.executeQuery();


            if (result.next()) {
                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setTitle(result.getString("title"));
                appointment.setDescription(result.getString("description"));
                appointment.setLocation(result.getString("location"));
                appointment.setContact(result.getString("contact"));
                appointment.setType(result.getString("type"));
                appointment.setUrl(result.getString("url"));

                LocalDateTime startUTC = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endUTC = result.getTimestamp("end").toLocalDateTime();
                ZonedDateTime startLocal = ZonedDateTime.ofInstant(startUTC.toInstant(ZoneOffset.UTC), localZoneId);
                ZonedDateTime endLocal = ZonedDateTime.ofInstant(endUTC.toInstant(ZoneOffset.UTC), localZoneId);

                appointment.setStart(startLocal);
                appointment.setEnd(endLocal);

            }
        } catch (SQLException e) {
            System.out.println("SQLException (overlap): " + e.getMessage());


        }

        return appointment;

    }



}