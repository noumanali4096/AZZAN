package com.example.nouman.azzan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class IteekaafActivity extends AppCompatActivity {

    TextView t1,t2,t3;
    TextView t10,t11,t12;
    Button b1,b2,b3;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteekaaf);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        t1=(TextView) findViewById( R.id.ittekaaf_userName);
        t2=(TextView) findViewById( R.id.start_date);
        t3=(TextView) findViewById( R.id.end_date);
        // b1=(Button) findViewById( R.id.nikkah_datebutton);
        //b2=(Button) findViewById( R.id.Nikkahtime_button);
        b3=(Button) findViewById( R.id.submit_request);
        t10=(TextView) findViewById( R.id.textView13);
        t11=(TextView) findViewById( R.id.textView14);
        t12=(TextView) findViewById( R.id.textView15);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
