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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NikkahActivity extends AppCompatActivity {

    EditText name,date,time;
    Button b1, b2, b3;
    Toolbar toolbar;
    String fourth;
    String mPhone;
    DatabaseReference databaseNikkah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nikkah);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        Intent intent2 = getIntent();

        fourth = intent2.getStringExtra("UserPhone3");
        mPhone = intent2.getStringExtra("mPhone");
        databaseNikkah= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/NikkahAppointment");
        name = (EditText) findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.editText7);
        time = (EditText) findViewById(R.id.editText8);

        b3 = (Button) findViewById(R.id.submit_request);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNikkahAppointment();
               name.setText("");
               date.setText("");
               time.setText("");
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void addNikkahAppointment(){
        String username =  name.getText().toString().trim();
        String s1 = date.getText().toString().trim();
        String s2 = time.getText().toString().trim();
        String s3="pending";





        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2) && !TextUtils.isEmpty(mPhone))
        {
            NikkahAppointment nikkahAppointment=new NikkahAppointment(username,s1,s2,fourth,s3);
            databaseNikkah.child(mPhone).child(fourth).setValue(nikkahAppointment);

            Toast.makeText(this,"Nikkah Request sent",Toast.LENGTH_LONG).show();
        }
        else {
            if(TextUtils.isEmpty(mPhone))
            {
                Toast.makeText(this,"You are not subscribed to any mosque!",Toast.LENGTH_LONG).show();
            }
            else {
                if (TextUtils.isEmpty(username)) {
                    name.setError("Enter name");
                }
                if (TextUtils.isEmpty(s1)) {
                    date.setError("Enter date");
                }
                if (TextUtils.isEmpty(s2)) {
                    time.setError("Enter time");
                }
            }
        }
    }
}