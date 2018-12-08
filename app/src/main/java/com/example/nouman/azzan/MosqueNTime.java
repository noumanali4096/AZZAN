package com.example.nouman.azzan;

import java.util.Comparator;

public class MosqueNTime {
    private  Mosque mosque;
    private PrayerTimmings prayerTimmings;
    private int distance;
    public MosqueNTime(){

    }

    public MosqueNTime(Mosque mosque, PrayerTimmings prayerTimmings,int distance) {
        this.mosque = mosque;
        this.prayerTimmings = prayerTimmings;
        this.distance=distance;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
