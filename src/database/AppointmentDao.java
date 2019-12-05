package database;

import entity.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;

import static controller.LoginScreenController.currentUser;
import static database.DBConnection.conn;

public class AppointmentDao {

    private static ZoneId localZoneId = ZoneId.systemDefault();

    private static ObservableList<Appointment> Appointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> getAllAppointmentsByUser() {

        Appointments.clear();

        try {


            String sqlStatement = "select * from appointment ORDER BY userId";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();

            while (result.next()) {

                Appointment appointment = new Appointment();
                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setUserId(result.getInt("userId"));
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


    public static ObservableList<Appointment> getAppointmentTypes() {

        Appointments.clear();

        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now().plusMonths(1);


        try {

            String sqlStatement = "select * from appointment where start >= '" + begin + "' and start <= '" + end + "' GROUP BY type";

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


            String sqlStatement = "select * from appointment where start >= '" + begin + "' and start <= '" + end + "' order by start";

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


            String sqlStatement = "select * from appointment where start >= '" + begin + "' and start <= '" + end + "' order by start";

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

        return timestampStart;

    }


    public static Timestamp endDateTimeConverter(Appointment appointment) {


        ZonedDateTime appointmentEndDateTime = appointment.getEnd();
        ZonedDateTime endLDT = appointmentEndDateTime;

        ZonedDateTime endToUTC = endLDT.withZoneSameInstant(ZoneId.of("UTC"));

        LocalDateTime endTime = endToUTC.toLocalDateTime();
        Timestamp timestampEnd = Timestamp.valueOf(endTime);

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


    public static void Appointment(Appointment appointment) {

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


            PreparedStatement statement = conn.prepareStatement(upcomingAppointment);
            ResultSet result = statement.executeQuery();

            while (result.next()) {


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


    //Model future queries after this one
    public static Appointment overlappingAppointmentAdd(Appointment appointment) {

        Timestamp timestampStart = startDateTimeConverter(appointment);
        Timestamp timestampEnd = endDateTimeConverter(appointment);


        try {


            String sqlStatement = "SELECT * FROM appointment "
                    + "WHERE (start >= ? AND end <= ?) "
                    + "OR (start <= ? AND end >= ?) "
                    + "OR (start BETWEEN ? AND ? OR end BETWEEN ? AND ?)";


            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setTimestamp(1, timestampStart);
            statement.setTimestamp(2, timestampEnd);
            statement.setTimestamp(3, timestampStart);
            statement.setTimestamp(4, timestampStart);
            statement.setTimestamp(5, timestampStart);
            statement.setTimestamp(6, timestampEnd);
            statement.setTimestamp(7, timestampStart);
            statement.setTimestamp(8, timestampEnd);
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


    public static Appointment overlappingAppointmentEdit(Appointment appointment, int appId) {

        Timestamp timestampStart = startDateTimeConverter(appointment);
        Timestamp timestampEnd = endDateTimeConverter(appointment);


        try {


            String sqlStatement = "SELECT * FROM appointment WHERE (start BETWEEN '" + timestampStart + "' and '" + timestampEnd + "') AND appointmentId != 1" + appId + "";

            QueryManager.makeQuery(sqlStatement);
            ResultSet result = QueryManager.getResult();


            if (result.next()) {


                appointment.setAppointmentId(result.getInt("appointmentId"));
                appointment.setCustomerId(result.getInt("customerId"));
                appointment.setUserId(result.getInt("userId"));
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