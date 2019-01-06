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
        /*
        double latitude = 31.5204;
        double longitude = 74.3587;
        double timezone = 5;
        PrayTime prayers = new PrayTime("hanafi");

        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();

        for (int i=0;i<prayerTimes.size();i++) {
            if (i != 1 && i !=4) {
                String timme = prayerTimes.get(i);
                ;
                String[] time = timme.split(":");
                String[] time2 = time[1].split(" ");
                int hour = Integer.parseInt(time[0].trim());
                int min = Integer.parseInt(time2[0].trim());
                String ampm = time2[1].trim();
                if (ampm.equals("am") && hour == 12) {
                    hour = 0;
                } else if (ampm.equals("pm") && hour < 12) {
                    hour = hour + 12;
                }

                cal.set(
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),
                        hour,
                        min,
                        0
                );
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent myintent = new Intent(context, PlayAlarm.class);
                if(i==0){
                    myintent.putExtra("namaz","Fajar at: "+timme);
                }
                else if(i==2){
                    myintent.putExtra("namaz","Zohar at: "+timme);
                }
                else if(i==3){
                    myintent.putExtra("namaz","Asar at: "+timme);
                }
                else if(i==5){
                    myintent.putExtra("namaz","Maghrib at: "+timme);
                }
                else if(i==6){
                    myintent.putExtra("namaz","Isha at: "+timme);
                }
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myintent, 0);
                alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        }
        */
        cal.set(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                5,
                12,
                0
        );
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myintent = new Intent(context, PlayAlarm.class);
        myintent.putExtra("namaz","Isha at: 07:30 am");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myintent, 0);
        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }
}
