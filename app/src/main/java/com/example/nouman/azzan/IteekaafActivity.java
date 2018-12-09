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

    EditText name;
    Button b3;
    Toolbar toolbar;
    DatabaseReference databaseIttekaaf;
    String fifth;
    String mPhone;
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
        mPhone = intent2.getStringExtra("mPhone");
        databaseIttekaaf= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/IttekaafAppointment");
        name=(EditText) findViewById( R.id.editText_nme);
        b3=(Button) findViewById( R.id.submit_request);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIttekaafAppointment();
                name.setText("");
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

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(mPhone) )
        {

            IttekaafAppointment ittekaafAppointment=new IttekaafAppointment(username,fifth,"pending");

            databaseIttekaaf.child(mPhone).child(fifth).setValue(ittekaafAppointment);

            Toast.makeText(this,"Ittekaaf Request sent",Toast.LENGTH_LONG).show();
        }
        else {
            if(!TextUtils.isEmpty(mPhone))
            {
                Toast.makeText(this,"You are not subscribed to any mosque!",Toast.LENGTH_LONG).show();
            }
            else {
                if (TextUtils.isEmpty(username)) {
                    name.setError("Enter name");
                }
            }

        }
    }
}
