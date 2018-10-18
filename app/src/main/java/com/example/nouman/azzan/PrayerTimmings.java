package com.example.nouman.azzan;

import java.io.Serializable;

public class PrayerTimmings implements Serializable {
    private String fajartime;
    private String zohartime;
    private String asartime;
    private String maghribtime;
    private String ishatime;
    private String jumatime;
    private String phoneNumber;

    public PrayerTimmings(){}

    public PrayerTimmings(String fajartime, String zohartime, String asartime, String maghribtime,
                         String ishatime, String jumatime,String pno) {
        this.fajartime = fajartime;
        this.zohartime = zohartime;
        this.asartime = asartime;
        this.maghribtime = maghribtime;
        this.ishatime = ishatime;
        this.jumatime = jumatime;
        this.phoneNumber=pno;
    }

    public String getFajartime() {
        return fajartime;
    }

    public void setFajartime(String fajartime) {
        this.fajartime = fajartime;
    }

    public String getZohartime() {
        return zohartime;
    }

    public void setZohartime(String zohartime) {
        this.zohartime = zohartime;
    }

    public String getAsartime() {
        return asartime;
    }

    public void setAsartime(String asartime) {
        this.asartime = asartime;
    }

    public String getMaghribtime() {
        return maghribtime;
    }

    public void setMaghribtime(String maghribtime) {
        this.maghribtime = maghribtime;
    }

    public String getIshatime() {
        return ishatime;
    }

    public void setIshatime(String ishatime) {
        this.ishatime = ishatime;
    }

    public String getJumatime() {
        return jumatime;
    }

    public void setJumatime(String jumatime) {
        this.jumatime = jumatime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
