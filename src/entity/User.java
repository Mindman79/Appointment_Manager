package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {


    private int userId;
    private String userName;
    private String userPassword;
    private LocalDate createDate;
    private String createdBy;
    private int userIsActive;
    private LocalDateTime userLastUpdate;
    private String userLastUpdatedBy;

    public User(int userId, String userName, String userPassword, LocalDate createDate, String createdBy, int userIsActive, LocalDateTime userLastUpdate, String userLastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.userIsActive = userIsActive;
        this.userLastUpdate = userLastUpdate;
        this.userLastUpdatedBy = userLastUpdatedBy;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(int userIsActive) {
        this.userIsActive = userIsActive;
    }

    public LocalDateTime getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(LocalDateTime userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public String getUserLastUpdatedBy() {
        return userLastUpdatedBy;
    }

    public void setUserLastUpdatedBy(String userLastUpdatedBy) {
        this.userLastUpdatedBy = userLastUpdatedBy;
    }
}
