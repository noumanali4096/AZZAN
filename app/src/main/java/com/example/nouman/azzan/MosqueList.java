package com.example.nouman.azzan;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MosqueList extends AppCompatActivity {

    DatabaseReference databasemosquentimming;
    ListView listmosq;
    List<MosqueNTime> mosqueNTimeLists;
    Mosque mosq;
    PrayerTimmings prayerTimmings;
    Toolbar toolbar;
    double clat;
    double clong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosque_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        listmosq = (ListView) findViewById(R.id.mosqlist);
        mosqueNTimeLists = new ArrayList<>();
        databasemosquentimming = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/mosquentiming");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        clat = intent.getDoubleExtra("lat",0);
        clong = intent.getDoubleExtra("long",0);
        Log.d("mosquelist", "i am here");
        databasemosquentimming.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mosqueNTimeLists.clear();
                for(DataSnapshot mosqSnapshot: dataSnapshot.getChildren()) {

                    MosqueNTime mosqueNTime = mosqSnapshot.getValue(MosqueNTime.class);
                    if(mosqueNTime!=null)
                    {
                        float results[] = new float[10];
                        Location.distanceBetween(clat,clong,mosqueNTime.getMosque().getLati(),mosqueNTime.getMosque().getLongitude(),results);
                        mosqueNTime.setDistance((int) results[0]);
                        mosqueNTimeLists.add(mosqueNTime);
                    }

                }

                Collections.sort(mosqueNTimeLists,new SortByDistance());

                MosqueNTimeList  adapter = new MosqueNTimeList(MosqueList.this,mosqueNTimeLists);

                listmosq.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
