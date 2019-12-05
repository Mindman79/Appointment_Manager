package entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {


    private int userId;
    private String userName;
    private String userPassword;
    private LocalDateTime createDate;
    private Boolean active;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;


    public User(int userId, String userName, String userPassword, LocalDateTime createDate, Boolean active, String createdBy, Timestamp lastUpdate, String lastUpdateBy) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.createDate = createDate;
        this.active = active;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
