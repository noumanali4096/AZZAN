package com.example.nouman.azzan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private  TextView tetxtView;
    private TextView fajar;
    private TextView zohar;
    private TextView asar;
    private TextView magrib;
    private TextView isha;
    private TextView jumma;
    private static final String DESCRIBABLE_KEY = "timmings_key";
    private static final String NAME_KEY = "name_key";
    private static final String MPHONE_KEY = "mphone_key";
    private static final String UPHONE_KEY = "uphone_key";
    private static final String CLAT_KEY = "clat_key";
    private static final String CLNG_KEY = "clng_key";
    private static final String DLAT_KEY = "dlat_key";
    private static final String DLNG_KEY = "dlng_key";
    private static final String SPHONE_KEY = "sphone_key";
    private PrayerTimmings timmingsToShow ;
    private String mosqueName;
    private ImageButton direction;
    private ImageButton subscribe;
    private LatLng current;
    private LatLng dest;
    private String mosquePhone;
    private String userPhone;
    DatabaseReference databaseMosqueSubscribe;

    public static BottomSheetDialog newInstance(PrayerTimmings modelToPass,String name,String mphone,String uphone,double cLat,double cLng,double dLat,double dLng) {
        BottomSheetDialog bottomSheetFragment = new BottomSheetDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY,modelToPass);
        bundle.putSerializable(NAME_KEY,name);
        bundle.putSerializable(MPHONE_KEY,mphone);
        bundle.putSerializable(UPHONE_KEY,uphone);
        bundle.putSerializable(CLAT_KEY,cLat);
        bundle.putSerializable(CLNG_KEY,cLng);
        bundle.putSerializable(DLAT_KEY,dLat);
        bundle.putSerializable(DLNG_KEY,dLng);
        //bundle.putSerializable(SPHONE_KEY,sPhone);
        bottomSheetFragment.setArguments(bundle);
        return bottomSheetFragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        databaseMosqueSubscribe = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/mosquesubscriber");

        timmingsToShow = (PrayerTimmings) getArguments().getSerializable(
                DESCRIBABLE_KEY);
        mosqueName = (String) getArguments().getSerializable(
                NAME_KEY);
        mosquePhone = (String) getArguments().getSerializable(
                MPHONE_KEY);
        userPhone = (String) getArguments().getSerializable(
                UPHONE_KEY);
        //sPhone = (String) getArguments().getSerializable(SPHONE_KEY);

        current = new LatLng((Double) getArguments().getSerializable(
                CLAT_KEY),(Double) getArguments().getSerializable(
                CLNG_KEY));
        dest = new LatLng((Double) getArguments().getSerializable(
                DLAT_KEY),(Double) getArguments().getSerializable(
                DLNG_KEY));
        direction = (ImageButton) v.findViewById(R.id.imageButton);
        subscribe = (ImageButton) v.findViewById(R.id.imageButton2);
        tetxtView = (TextView) v.findViewById(R.id.textView);
        tetxtView.setText(mosqueName);
        fajar = (TextView) v.findViewById(R.id.fajar_time_textView);
        fajar.setText(timmingsToShow.getFajartime());
        zohar = (TextView) v.findViewById(R.id.zohar_time_textView);
        zohar.setText(timmingsToShow.getZohartime());
        asar = (TextView) v.findViewById(R.id.asar_time_textView);
        asar.setText(timmingsToShow.getAsartime());
        magrib = (TextView) v.findViewById(R.id.magrib_time_textView);
        magrib.setText(timmingsToShow.getMaghribtime());
        isha = (TextView) v.findViewById(R.id.isha_time_textView);
        isha.setText(timmingsToShow.getIshatime());
        jumma = (TextView) v.findViewById(R.id.juma_time_textView);
        jumma.setText(timmingsToShow.getJumatime());

        Query fireBaseQuery = databaseMosqueSubscribe.orderByChild("userPhone").equalTo(userPhone);

        fireBaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.exists()) {

                // dataSnapshot is the "issue" node with all children with id 0
                for (DataSnapshot mosquesubscribeSnapshot : dataSnapshot.getChildren()) {
                    MosqueSubcribe mosqueSubcribe =  mosquesubscribeSnapshot.getValue(MosqueSubcribe.class);
                    if(mosqueSubcribe.getMosquePhone().equals(mosquePhone))
                    {
                        subscribe.setImageResource(R.drawable.fui_done_check_mark);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent=new Intent(getContext(),DirectionActivity.class);
                intent.putExtra("cmarker",current);
                intent.putExtra("dmarker",dest);
                intent.putExtra("name",mosqueName);
                startActivityForResult(intent,1);
                */

                String uri="google.navigation:q="+dest.latitude+","+dest.longitude;
                Uri gmmIntentUri = Uri.parse(uri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query fireBaseQuery = databaseMosqueSubscribe.orderByChild("userPhone").equalTo(userPhone);

                fireBaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot mosquesubscribeSnapshot : dataSnapshot.getChildren()) {
                                MosqueSubcribe mosqueSubcribe = mosquesubscribeSnapshot.getValue(MosqueSubcribe.class);
                                String topic = "mosqueTiming" + mosqueSubcribe.getMosquePhone();
                                FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                                MosqueSubcribe obj = new MosqueSubcribe(mosquePhone,userPhone);
                                databaseMosqueSubscribe.child(userPhone).setValue(obj);
                                topic="mosqueTiming" + mosquePhone;
                                FirebaseMessaging.getInstance().subscribeToTopic(topic);
                                subscribe.setImageResource(R.drawable.fui_done_check_mark);
                            }
                        }
                        else {
                            MosqueSubcribe obj = new MosqueSubcribe(mosquePhone,userPhone);
                            databaseMosqueSubscribe.child(userPhone).setValue(obj);
                            String topic="mosqueTiming" + mosquePhone;
                            FirebaseMessaging.getInstance().subscribeToTopic(topic);
                            subscribe.setImageResource(R.drawable.fui_done_check_mark);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
        return v;
    }

}
