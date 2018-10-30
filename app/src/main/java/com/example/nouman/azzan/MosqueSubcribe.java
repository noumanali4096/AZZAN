package com.example.nouman.azzan;

public class MosqueSubcribe {
    private String mosquePhone;
    private String userPhone;
    public MosqueSubcribe(){

    }

    public MosqueSubcribe(String mosquePhone, String userPhone) {
        this.mosquePhone = mosquePhone;
        this.userPhone = userPhone;
    }

    public String getMosquePhone() {
        return mosquePhone;
    }

    public void setMosquePhone(String mosquePhone) {
        this.mosquePhone = mosquePhone;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
