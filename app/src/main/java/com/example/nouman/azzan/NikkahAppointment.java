package com.example.nouman.azzan;

public class NikkahAppointment {
    private String name;
    private String date;
    private String time;
    private String phoneNo;
    private String status;

    public NikkahAppointment(){}
    public NikkahAppointment(String name, String date, String time, String phoneNo,String s) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.phoneNo = phoneNo;
        this.status=s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
