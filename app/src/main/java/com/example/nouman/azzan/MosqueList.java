package com.example.nouman.azzan;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MosqueList extends AppCompatActivity {

    DatabaseReference databasemosq;
    DatabaseReference databasePrayertimming;
    ListView listmosq;
    List<MosqueNTime> mosqueNTimeLists;
    Mosque mosq;
    PrayerTimmings prayerTimmings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosque_list);
        listmosq = (ListView) findViewById(R.id.mosqlist);
        mosqueNTimeLists = new ArrayList<>();
        databasemosq = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/mosque");
        databasePrayertimming = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/prayertimings");
    }

    @Override
    protected void onStart() {
        super.onStart();

        databasemosq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mosqueNTimeLists.clear();
                for(DataSnapshot mosqSnapshot: dataSnapshot.getChildren()) {

                    mosq = mosqSnapshot.getValue(Mosque.class);
                    if(mosq!=null)
                    {
                        Query fireBaseQuery = databasePrayertimming.orderByChild("phoneNumber").equalTo(mosq.getPhone());

                        fireBaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                //if (dataSnapshot.exists()) {

                                // dataSnapshot is the "issue" node with all children with id 0
                                for (DataSnapshot mosqueadminSnapshot : dataSnapshot.getChildren()) {
                                    prayerTimmings =  mosqueadminSnapshot.getValue(PrayerTimmings.class);
                                    if(prayerTimmings!=null)
                                    {
                                        MosqueNTime obj = new MosqueNTime(mosq,prayerTimmings,1000);
                                        mosqueNTimeLists.add(obj);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                }

                MosqueNTimeList  adapter = new MosqueNTimeList(MosqueList.this,mosqueNTimeLists);

                listmosq.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
