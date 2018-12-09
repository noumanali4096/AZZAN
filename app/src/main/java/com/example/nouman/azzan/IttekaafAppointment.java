package com.example.nouman.azzan;

public class IttekaafAppointment {
    private String name;
    private String phoneNo;
    private String status;

    public IttekaafAppointment(){}
    public IttekaafAppointment(String name,String phoneNo, String status) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
