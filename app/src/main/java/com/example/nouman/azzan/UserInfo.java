package com.example.nouman.azzan;

public class UserInfo {
    private String name;
    private String address;
    private String city;
    private String phoneNo;

    public UserInfo(){}
    public UserInfo(String name, String address, String city, String phoneNo) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
