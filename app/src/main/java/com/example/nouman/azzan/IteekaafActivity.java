package com.example.nouman.azzan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IteekaafActivity extends AppCompatActivity {

    EditText name,date,time;
    Button b1,b2,b3;
    Toolbar toolbar;
    DatabaseReference databaseIttekaaf;
    String fifth;
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
        Intent intent2 = getIntent();

        fifth = intent2.getStringExtra("UserPhone4");
        databaseIttekaaf= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/IttekaafAppointment");
        name=(EditText) findViewById( R.id.editText_nme);
        date=(EditText) findViewById( R.id.editText_date);
        time=(EditText) findViewById( R.id.editText_time);
        // b1=(Button) findViewById( R.id.nikkah_datebutton);
        //b2=(Button) findViewById( R.id.Nikkahtime_button);
        b3=(Button) findViewById( R.id.submit_request);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIttekaafAppointment();
                name.setText("");
                date.setText("");
                time.setText("");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private  void addIttekaafAppointment(){
        String username =  name.getText().toString().trim();
        String s1 = date.getText().toString().trim();
        String s2 = time.getText().toString().trim();


        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2))
        {

            IttekaafAppointment ittekaafAppointment=new IttekaafAppointment(username,s1,s2,fifth);

            databaseIttekaaf.child(fifth).setValue(ittekaafAppointment);

            Toast.makeText(this,"Ittekaaf Request sent",Toast.LENGTH_LONG).show();
        }
        else {
            if(TextUtils.isEmpty(username)){
                name.setError("Enter name");
            }
            if(TextUtils.isEmpty(s1)){
                date.setError("Enter Start date");
            }
            if(TextUtils.isEmpty(s2)){
                time.setError("Enter End date");
            }

        }
    }
}
