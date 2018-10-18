package com.example.nouman.azzan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import java.util.Calendar;
import android.view.View.OnClickListener;
import android.view.View;



public class NamazAlarmFragment extends Fragment implements View.OnClickListener {

    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_namaz_alarm, container, false);

        EditText e = (EditText) v.findViewById(R.id.editText1);
        e.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editText1:
                chooseTime=(EditText) v.findViewById(R.id.editText1);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;
        }
    }


}
