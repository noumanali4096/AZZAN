package com.example.nouman.azzan;

public class IttekaafAppointment {
    private String name;
    private String startDate;
    private String endDate;
    private String phoneNo;

    public IttekaafAppointment(){}
    public IttekaafAppointment(String name, String startDate, String endDate, String phoneNo) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
