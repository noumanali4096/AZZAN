package com.example.nouman.azzan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserDetail extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        e1=(EditText) findViewById(R.id.name_editText);
        e2=(EditText) findViewById(R.id.address_editText);
        e3=(EditText) findViewById(R.id.city_editText);
        b1=(Button) findViewById(R.id.done_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDetail.this,UserHomeScreen.class);
                startActivity(intent);
            }
        });
    }
}
