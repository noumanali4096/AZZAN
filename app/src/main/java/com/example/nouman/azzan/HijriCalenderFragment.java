package com.example.nouman.azzan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class HijriCalenderFragment extends Fragment {

    ImageView im;
    View view;
    TextView date1,fajar1,sunrise1,zohar1,asar1,sunset1,maghrib1,isha1;
    TextView date2,fajar2,sunrise2,zohar2,asar2,sunset2,maghrib2,isha2;
    TextView date3,fajar3,sunrise3,zohar3,asar3,sunset3,maghrib3,isha3;
    TextView date4,fajar4,sunrise4,zohar4,asar4,sunset4,maghrib4,isha4;
    TextView date5,fajar5,sunrise5,zohar5,asar5,sunset5,maghrib5,isha5;
    TextView date6,fajar6,sunrise6,zohar6,asar6,sunset6,maghrib6,isha6;
    TextView date7,fajar7,sunrise7,zohar7,asar7,sunset7,maghrib7,isha7;
    String dayOfTheWeek,day, monthString, monthNumber, year;
    ArrayList<String> prayerTimes,prayerNames;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_hijri_calender, container, false);

        date1 = (TextView) view.findViewById(R.id.date1);
        fajar1= (TextView) view.findViewById(R.id.fajar1);
        sunrise1= (TextView) view.findViewById(R.id.sunrise1);
        zohar1= (TextView) view.findViewById(R.id.zohar1);
        asar1= (TextView) view.findViewById(R.id.asar1);
        sunset1= (TextView) view.findViewById(R.id.sunset1);
        maghrib1= (TextView) view.findViewById(R.id.maghrib1);
        isha1= (TextView) view.findViewById(R.id.isha1);

        date2 = (TextView) view.findViewById(R.id.date2);
        fajar2= (TextView) view.findViewById(R.id.fajar2);
        sunrise2= (TextView) view.findViewById(R.id.sunrise2);
        zohar2= (TextView) view.findViewById(R.id.zohar2);
        asar2= (TextView) view.findViewById(R.id.asar2);
        sunset2= (TextView) view.findViewById(R.id.sunset2);
        maghrib2= (TextView) view.findViewById(R.id.maghrib2);
        isha2= (TextView) view.findViewById(R.id.isha2);

        date3 = (TextView) view.findViewById(R.id.date3);
        fajar3= (TextView) view.findViewById(R.id.fajar3);
        sunrise3= (TextView) view.findViewById(R.id.sunrise3);
        zohar3= (TextView) view.findViewById(R.id.zohar3);
        asar3= (TextView) view.findViewById(R.id.asar3);
        sunset3= (TextView) view.findViewById(R.id.sunset3);
        maghrib3= (TextView) view.findViewById(R.id.maghrib3);
        isha3= (TextView) view.findViewById(R.id.isha3);

        date4 = (TextView) view.findViewById(R.id.date4);
        fajar4= (TextView) view.findViewById(R.id.fajar4);
        sunrise4= (TextView) view.findViewById(R.id.sunrise4);
        zohar4= (TextView) view.findViewById(R.id.zohar4);
        asar4= (TextView) view.findViewById(R.id.asar4);
        sunset4= (TextView) view.findViewById(R.id.sunset4);
        maghrib4= (TextView) view.findViewById(R.id.maghrib4);
        isha4= (TextView) view.findViewById(R.id.isha4);

        date5 = (TextView) view.findViewById(R.id.date5);
        fajar5= (TextView) view.findViewById(R.id.fajar5);
        sunrise5= (TextView) view.findViewById(R.id.sunrise5);
        zohar5= (TextView) view.findViewById(R.id.zohar5);
        asar5= (TextView) view.findViewById(R.id.asar5);
        sunset5= (TextView) view.findViewById(R.id.sunset5);
        maghrib5= (TextView) view.findViewById(R.id.maghrib5);
        isha5= (TextView) view.findViewById(R.id.isha5);

        date6 = (TextView) view.findViewById(R.id.date6);
        fajar6= (TextView) view.findViewById(R.id.fajar6);
        sunrise6= (TextView) view.findViewById(R.id.sunrise6);
        zohar6= (TextView) view.findViewById(R.id.zohar6);
        asar6= (TextView) view.findViewById(R.id.asar6);
        sunset6= (TextView) view.findViewById(R.id.sunset6);
        maghrib6= (TextView) view.findViewById(R.id.maghrib6);
        isha6= (TextView) view.findViewById(R.id.isha6);

        date7 = (TextView) view.findViewById(R.id.date7);
        fajar7= (TextView) view.findViewById(R.id.fajar7);
        sunrise7= (TextView) view.findViewById(R.id.sunrise7);
        zohar7= (TextView) view.findViewById(R.id.zohar7);
        asar7= (TextView) view.findViewById(R.id.asar7);
        sunset7= (TextView) view.findViewById(R.id.sunset7);
        maghrib7= (TextView) view.findViewById(R.id.maghrib7);
        isha7= (TextView) view.findViewById(R.id.isha7);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        double latitude = 31.5204;
        double longitude = 74.3587;
        double timezone = 5;
        PrayTime prayers = new PrayTime("hanafi","12");

        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);

        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date1.setText(day+"/"+monthNumber+"/"+year);
        fajar1.setText(prayerTimes.get(0));
        sunrise1.setText(prayerTimes.get(1));
        zohar1.setText(prayerTimes.get(2));
        asar1.setText(prayerTimes.get(3));
        sunset1.setText(prayerTimes.get(4));
        maghrib1.setText(prayerTimes.get(5));
        isha1.setText(prayerTimes.get(6));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        now = cal.getTime();
        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date2.setText(day+"/"+monthNumber+"/"+year);
        fajar2.setText(prayerTimes.get(0));
        sunrise2.setText(prayerTimes.get(1));
        zohar2.setText(prayerTimes.get(2));
        asar2.setText(prayerTimes.get(3));
        sunset2.setText(prayerTimes.get(4));
        maghrib2.setText(prayerTimes.get(5));
        isha2.setText(prayerTimes.get(6));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        now = cal.getTime();
        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date3.setText(day+"/"+monthNumber+"/"+year);
        fajar3.setText(prayerTimes.get(0));
        sunrise3.setText(prayerTimes.get(1));
        zohar3.setText(prayerTimes.get(2));
        asar3.setText(prayerTimes.get(3));
        sunset3.setText(prayerTimes.get(4));
        maghrib3.setText(prayerTimes.get(5));
        isha3.setText(prayerTimes.get(6));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        now = cal.getTime();
        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date4.setText(day+"/"+monthNumber+"/"+year);
        fajar4.setText(prayerTimes.get(0));
        sunrise4.setText(prayerTimes.get(1));
        zohar4.setText(prayerTimes.get(2));
        asar4.setText(prayerTimes.get(3));
        sunset4.setText(prayerTimes.get(4));
        maghrib4.setText(prayerTimes.get(5));
        isha4.setText(prayerTimes.get(6));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        now = cal.getTime();
        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date5.setText(day+"/"+monthNumber+"/"+year);
        fajar5.setText(prayerTimes.get(0));
        sunrise5.setText(prayerTimes.get(1));
        zohar5.setText(prayerTimes.get(2));
        asar5.setText(prayerTimes.get(3));
        sunset5.setText(prayerTimes.get(4));
        maghrib5.setText(prayerTimes.get(5));
        isha5.setText(prayerTimes.get(6));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        now = cal.getTime();
        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date6.setText(day+"/"+monthNumber+"/"+year);
        fajar6.setText(prayerTimes.get(0));
        sunrise6.setText(prayerTimes.get(1));
        zohar6.setText(prayerTimes.get(2));
        asar6.setText(prayerTimes.get(3));
        sunset6.setText(prayerTimes.get(4));
        maghrib6.setText(prayerTimes.get(5));
        isha6.setText(prayerTimes.get(6));

        cal.add(Calendar.DAY_OF_YEAR, 1);
        now = cal.getTime();
        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        dayOfTheWeek = (String) DateFormat.format("EEEE", now); // Thursday
        day = (String) DateFormat.format("dd",   now); // 20
        monthString  = (String) DateFormat.format("MMM",  now); // Jun
        monthNumber  = (String) DateFormat.format("MM",   now); // 06
        year         = (String) DateFormat.format("yyyy", now); // 2013
        date7.setText(day+"/"+monthNumber+"/"+year);
        fajar7.setText(prayerTimes.get(0));
        sunrise7.setText(prayerTimes.get(1));
        zohar7.setText(prayerTimes.get(2));
        asar7.setText(prayerTimes.get(3));
        sunset7.setText(prayerTimes.get(4));
        maghrib7.setText(prayerTimes.get(5));
        isha7.setText(prayerTimes.get(6));

        return  view;
    }

}
