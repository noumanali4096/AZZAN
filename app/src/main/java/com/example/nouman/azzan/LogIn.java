package com.example.nouman.azzan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        textView=(TextView) findViewById(R.id.textphone);
        if(getIntent()!=null)
        {
            textView.setText(getIntent().getStringExtra("phone"));
        }
    }
}
