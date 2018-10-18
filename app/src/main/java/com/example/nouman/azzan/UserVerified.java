package com.example.nouman.azzan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserVerified extends AppCompatActivity {

    TextView t1;
    ImageView i1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_verified);
        t1=(TextView) findViewById(R.id.verified_text);
        i1=(ImageView) findViewById(R.id.verified_imageView);
        b1=(Button) findViewById(R.id.next_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserVerified.this,UserDetail.class);
                startActivity(intent);
            }
        });
    }
}