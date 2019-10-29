package entity;

import javafx.beans.value.ObservableValue;

public class TableRow {


    private ObservableValue<String> customerName;
    private ObservableValue<String> phone;
    private ObservableValue<String> address;
    private ObservableValue<String> city;
    private ObservableValue<String> postalCode;


    public TableRow(ObservableValue<String> customerName, ObservableValue<String> phone, ObservableValue<String> address, ObservableValue<String> city, ObservableValue<String> postalCode) {
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }


    public ObservableValue<String> getCustomerName() {
        return customerName;
    }

    public void setCustomerName(ObservableValue<String> customerName) {
        this.customerName = customerName;
    }

    public ObservableValue<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableValue<String> phone) {
        this.phone = phone;
    }

    public void setAddress(ObservableValue<String> address) {
        this.address = address;
    }

    public ObservableValue<String> getAddress() {
        return address;
    }


    public void setCity(ObservableValue<String> city) {
        this.city = city;
    }

    public ObservableValue<String> getCity() {
        return city;
    }


    public void setPostalCode(ObservableValue<String> postalCode) {
        this.postalCode = postalCode;
    }

    public ObservableValue<String> getPostalCode() {
        return postalCode;
    }


}
