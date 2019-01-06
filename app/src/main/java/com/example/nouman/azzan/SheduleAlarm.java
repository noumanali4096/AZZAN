package com.example.nouman.azzan;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class SheduleAlarm extends BroadcastReceiver  {
    private ArrayList<String> prayerTimes,prayerNames;
    public SheduleAlarm() {
        super();

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        double latitude = 31.5204;
        double longitude = 74.3587;
        double timezone = 5;
        PrayTime prayers = new PrayTime("hanafi","24");

        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();

        String timme = prayerTimes.get(0);
        String[] time = timme.split(":");
        int h = Integer.parseInt(time[0].trim());
        int m = Integer.parseInt(time[1].trim());

        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                h,
                m,
                0
        );
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent = new Intent(context, PlayAlarm.class);
        myintent.putExtra("namaz","Fajar at: "+timme);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myintent, 0);
        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);



        timme = prayerTimes.get(2);
        time = timme.split(":");
        h = Integer.parseInt(time[0].trim());
        m = Integer.parseInt(time[1].trim());

        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                h,
                m,
                0
        );
        AlarmManager alarmManager1 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent1 = new Intent(context, PlayAlarm.class);
        myintent1.putExtra("namaz","Zohar at: "+timme);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, myintent1, 0);
        alarmManager1.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent1);
        alarmManager1.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent1);

        timme = prayerTimes.get(3);
        time = timme.split(":");
        h = Integer.parseInt(time[0].trim());
        m = Integer.parseInt(time[1].trim());

        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                h,
                m,
                0
        );
        AlarmManager alarmManager2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent2 = new Intent(context, PlayAlarm.class);
        myintent2.putExtra("namaz","Asar at: "+timme);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 0, myintent2, 0);
        alarmManager2.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent2);
        alarmManager2.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent2);

        timme = prayerTimes.get(5);
        time = timme.split(":");
        h = Integer.parseInt(time[0].trim());
        m = Integer.parseInt(time[1].trim());

        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                h,
                m,
                0
        );
        AlarmManager alarmManager3 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent3 = new Intent(context, PlayAlarm.class);
        myintent3.putExtra("namaz","Maghrib at: "+timme);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(context, 0, myintent3, 0);
        alarmManager3.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent3);
        alarmManager3.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent3);



        timme = prayerTimes.get(6);
        time = timme.split(":");
        h = Integer.parseInt(time[0].trim());
        m = Integer.parseInt(time[1].trim());

        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                h,
                m,
                0
        );
        AlarmManager alarmManager4 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent4 = new Intent(context, PlayAlarm.class);
        myintent4.putExtra("namaz","Isha at: "+timme);
        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, 0, myintent4, 0);
        alarmManager4.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent4);
        alarmManager4.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent4);

        /*
        String timme = "15:45";
        String[] time = timme.split(":");
        int h = Integer.parseInt(time[0].trim());
        int m = Integer.parseInt(time[1].trim());

        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                h,
                m,
                0
        );
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent = new Intent(context, PlayAlarm.class);
        myintent.putExtra("namaz","Isha at: 07:30 am");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myintent, 0);
        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        */
    }
}
