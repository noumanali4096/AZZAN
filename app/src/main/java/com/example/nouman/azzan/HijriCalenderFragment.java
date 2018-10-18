package com.example.nouman.azzan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HijriCalenderFragment extends Fragment {

    ImageView im;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_hijri_calender, container, false);
        im=(ImageView) view.findViewById(R.id.calender_imageView);
        return  view;
    }


}
