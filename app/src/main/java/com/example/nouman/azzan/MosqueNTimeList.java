package com.example.nouman.azzan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MosqueNTimeList  extends ArrayAdapter<MosqueNTime> {
    private Activity context;
    private List<MosqueNTime> mosqueNTimeList;

    public MosqueNTimeList(AppCompatActivity context, List<MosqueNTime> mosqueNTimeListList){
        super(context,R.layout.list_layout,mosqueNTimeListList);
        this.context=context;
        this.mosqueNTimeList=mosqueNTimeListList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.Mosqname);
        TextView textViewtime = (TextView) listViewItem.findViewById(R.id.prayertime);
        TextView textViewdist = (TextView) listViewItem.findViewById(R.id.dist);


        MosqueNTime mosqueNTime = mosqueNTimeList.get(position);

        textViewName.setText(mosqueNTime.getMosque().getName());
        textViewtime.setText("jumma: " +mosqueNTime.getPrayerTimmings().getJumatime());
        textViewdist.setText("100 meters");

        return listViewItem;
    }
}
