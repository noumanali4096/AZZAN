package com.example.nouman.azzan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

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
    private static final String CLAT_KEY = "clat_key";
    private static final String CLNG_KEY = "clng_key";
    private static final String DLAT_KEY = "dlat_key";
    private static final String DLNG_KEY = "dlng_key";
    private PrayerTimmings timmingsToShow ;
    private String mosqueName;
    private ImageButton direction;
    private LatLng current;
    private LatLng dest;

    public static BottomSheetDialog newInstance(PrayerTimmings modelToPass,String name,double cLat,double cLng,double dLat,double dLng) {
        BottomSheetDialog bottomSheetFragment = new BottomSheetDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY,modelToPass);
        bundle.putSerializable(NAME_KEY,name);
        bundle.putSerializable(CLAT_KEY,cLat);
        bundle.putSerializable(CLNG_KEY,cLng);
        bundle.putSerializable(DLAT_KEY,dLat);
        bundle.putSerializable(DLNG_KEY,dLng);
        bottomSheetFragment.setArguments(bundle);
        return bottomSheetFragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        timmingsToShow = (PrayerTimmings) getArguments().getSerializable(
                DESCRIBABLE_KEY);
        mosqueName = (String) getArguments().getSerializable(
                NAME_KEY);
        current = new LatLng((Double) getArguments().getSerializable(
                CLAT_KEY),(Double) getArguments().getSerializable(
                CLNG_KEY));
        dest = new LatLng((Double) getArguments().getSerializable(
                DLAT_KEY),(Double) getArguments().getSerializable(
                DLNG_KEY));
        direction = (ImageButton) v.findViewById(R.id.imageButton);
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
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),DirectionActivity.class);
                intent.putExtra("cmarker",current);
                intent.putExtra("dmarker",dest);
                intent.putExtra("name",mosqueName);
                startActivityForResult(intent,1);
            }
        });
        return v;
    }

}
