package entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {

    private int apptId;
    private int custId;
    private LocalDateTime apptStart;
    private LocalDateTime apptEnd;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptContact;
    private String apptType;
    private String apptUrl;
    private LocalDate apptCreateDate;
    private String apptCreatedBy;
    private LocalDateTime apptLastUpdate;
    private String apptLastUpdatedBy;

    public Appointment(int apptId, int custId, LocalDateTime apptStart, LocalDateTime apptEnd, String apptTitle, String apptDescription, String apptLocation, String apptContact, String apptType, String apptUrl, LocalDate apptCreateDate, String apptCreatedBy, LocalDateTime apptLastUpdate, String apptLastUpdatedBy) {
        this.apptId = apptId;
        this.custId = custId;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.apptUrl = apptUrl;
        this.apptCreateDate = apptCreateDate;
        this.apptCreatedBy = apptCreatedBy;
        this.apptLastUpdate = apptLastUpdate;
        this.apptLastUpdatedBy = apptLastUpdatedBy;
    }


    public Appointment() {
    }


    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public LocalDateTime getApptStart() {
        return apptStart;
    }

    public void setApptStart(LocalDateTime apptStart) {
        this.apptStart = apptStart;
    }

    public LocalDateTime getApptEnd() {
        return apptEnd;
    }

    public void setApptEnd(LocalDateTime apptEnd) {
        this.apptEnd = apptEnd;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public String getApptContact() {
        return apptContact;
    }

    public void setApptContact(String apptContact) {
        this.apptContact = apptContact;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public String getApptUrl() {
        return apptUrl;
    }

    public void setApptUrl(String apptUrl) {
        this.apptUrl = apptUrl;
    }

    public LocalDate getApptCreateDate() {
        return apptCreateDate;
    }

    public void setApptCreateDate(LocalDate apptCreateDate) {
        this.apptCreateDate = apptCreateDate;
    }

    public String getApptCreatedBy() {
        return apptCreatedBy;
    }

    public void setApptCreatedBy(String apptCreatedBy) {
        this.apptCreatedBy = apptCreatedBy;
    }

    public LocalDateTime getApptLastUpdate() {
        return apptLastUpdate;
    }

    public void setApptLastUpdate(LocalDateTime apptLastUpdate) {
        this.apptLastUpdate = apptLastUpdate;
    }

    public String getApptLastUpdatedBy() {
        return apptLastUpdatedBy;
    }

    public void setApptLastUpdatedBy(String apptLastUpdatedBy) {
        this.apptLastUpdatedBy = apptLastUpdatedBy;
    }
}