package com.example.nouman.azzan;

public class Mosque {

    private String name;
    private  double longitude;
    private  double lati;
    private  String phone;

    public Mosque(String name, double longitude, double lati, String phone) {

        this.name = name;
        this.longitude = longitude;
        this.lati = lati;
        this.phone = phone;
    }

    public Mosque(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
