package com.example.nouman.azzan;

public class MosqueNTime {
    private  Mosque mosque;
    private PrayerTimmings prayerTimmings;
    public MosqueNTime(){

    }

    public MosqueNTime(Mosque mosque, PrayerTimmings prayerTimmings,double distance) {
        this.mosque = mosque;
        this.prayerTimmings = prayerTimmings;
    }

    public Mosque getMosque() {
        return mosque;
    }

    public void setMosque(Mosque mosque) {
        this.mosque = mosque;
    }

    public PrayerTimmings getPrayerTimmings() {
        return prayerTimmings;
    }

    public void setPrayerTimmings(PrayerTimmings prayerTimmings) {
        this.prayerTimmings = prayerTimmings;
    }


}
