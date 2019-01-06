package com.example.nouman.azzan;

public class AddressModel {
    private String  phone,address;
    public AddressModel(){}
    public AddressModel(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
