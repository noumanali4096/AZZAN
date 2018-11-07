package com.example.nouman.azzan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.app.TimePickerDialog;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.view.View.OnClickListener;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class NamazAlarmFragment extends Fragment {

    DatabaseReference databaseMosqueSub;
    DatabaseReference databaseMosquetiming;
    DatabaseReference databaseMosque;
    EditText e1,e2,e3,e4,e5,e6;
    TextView t7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_namaz_alarm, container, false);

        e1 = (EditText) v.findViewById(R.id.editText1);
        e2 = (EditText) v.findViewById(R.id.editText2);
        e3 = (EditText) v.findViewById(R.id.editText3);
        e4 = (EditText) v.findViewById(R.id.editText4);;
        e5 = (EditText) v.findViewById(R.id.editText5);
        e6 = (EditText) v.findViewById(R.id.editText6);
        t7 = (TextView) v.findViewById(R.id.textView7);
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        e6.setEnabled(false);

        Intent intent = getActivity().getIntent();

        String phone = intent.getStringExtra("UserInfo2");
        databaseMosque= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/mosque");
        databaseMosqueSub= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/mosquesubscriber");
        databaseMosquetiming= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/prayertiming");

        Query fireBaseQueryMsubscriber = databaseMosqueSub.orderByChild("userPhone").equalTo(phone);

        fireBaseQueryMsubscriber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.exists()) {

                // dataSnapshot is the "issue" node with all children with id 0
                for (DataSnapshot mosqueSubSnapshot : dataSnapshot.getChildren()) {
                    MosqueSubcribe obj =  mosqueSubSnapshot.getValue(MosqueSubcribe.class);
                    String mPhone = obj.getMosquePhone();
                    if(!mPhone.isEmpty()){
                        Query fireBaseQueryMosqueTiming= databaseMosquetiming.orderByChild("phoneNumber").equalTo(mPhone);
                        Query fireBaseQueryMosque= databaseMosque.orderByChild("phone").equalTo(mPhone);
                        fireBaseQueryMosqueTiming.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                //if (dataSnapshot.exists()) {

                                // dataSnapshot is the "issue" node with all children with id 0
                                for (DataSnapshot mosqueTimingSnapshot : dataSnapshot.getChildren()) {
                                    PrayerTimmings prayerTImings =  mosqueTimingSnapshot.getValue(PrayerTimmings.class);
                                    e1.setText(prayerTImings.getFajartime());
                                    e2.setText(prayerTImings.getZohartime());
                                    e3.setText(prayerTImings.getAsartime());
                                    e4.setText(prayerTImings.getMaghribtime());
                                    e5.setText(prayerTImings.getIshatime());
                                    e6.setText(prayerTImings.getJumatime());

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        fireBaseQueryMosque.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                //if (dataSnapshot.exists()) {

                                // dataSnapshot is the "issue" node with all children with id 0
                                for (DataSnapshot mosqueSnapshot : dataSnapshot.getChildren()) {
                                    Mosque mosque =  mosqueSnapshot.getValue(Mosque.class);
                                    t7.setText(mosque.getName());
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }



}
