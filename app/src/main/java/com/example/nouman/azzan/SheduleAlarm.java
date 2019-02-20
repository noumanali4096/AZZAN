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

        MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.azan1);
        mediaPlayer.start();

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
        AlarmManager mgrAlarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        for(int i = 0; i < prayerTimes.size(); ++i)
        {
            if(i != 1 && i !=4){
                String timme = prayerTimes.get(i);
                String[] time = timme.split(":");
                int h = Integer.parseInt(time[0].trim());
                int m = Integer.parseInt(time[1].trim());
                Calendar calendar = Calendar.getInstance();
                calendar.set(
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),
                        h,
                        m,
                        0
                );
                Intent mintent = new Intent(context, PlayAlarm.class);
                if(i==0) {
                    mintent.putExtra("namaz", "Fajar at: " + timme);
                }
                else if(i==2) {
                    mintent.putExtra("namaz", "Zohar at: " + timme);
                }
                else if(i==3) {
                    mintent.putExtra("namaz", "Asar at: " + timme);
                }
                else if(i==5) {
                        mintent.putExtra("namaz", "Maghrib at: " + timme);
                }
                else if(i==6) {
                    mintent.putExtra("namaz", "Maghrib at: " + timme);
                }
                // Loop counter `i` is used as a `requestCode`
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, mintent, 0);
                // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
                mgrAlarm.set(AlarmManager.RTC,cal.getTimeInMillis(),
                        pendingIntent);
                mgrAlarm.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),
                        pendingIntent);

                intentArray.add(pendingIntent);
            }
        }





        /*
        String timme = "12:09";
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
        myintent.putExtra("namaz","Zohar at: 12:09 pm");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myintent, 0);
        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        */
    }
}
