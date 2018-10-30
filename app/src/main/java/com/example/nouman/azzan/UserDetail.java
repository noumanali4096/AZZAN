package com.example.nouman.azzan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetail extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1;
    String second;
    DatabaseReference databaseUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        e1=(EditText) findViewById(R.id.name_editText);
        e2=(EditText) findViewById(R.id.address_editText);
        e3=(EditText) findViewById(R.id.city_editText);
        b1=(Button) findViewById(R.id.done_button);
        databaseUserInfo= FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/UserInformation");
        Intent intent2 = getIntent();

        second = intent2.getStringExtra("UserPhoneInfo");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserinfo();
                Intent intent=new Intent(UserDetail.this,UserHomeScreen.class);
                intent.putExtra("UserInfo2",second);
                startActivityForResult(intent,3);
            }
        });


    }
    private void addUserinfo(){
        String name =  e1.getText().toString().trim();
        String s1 = e2.getText().toString();
        String s2 = e3.getText().toString();


        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2))
        {

            UserInfo userInfo=new UserInfo(name,s1,s2,second);
            databaseUserInfo.child(second).setValue(userInfo);

            Toast.makeText(this,"User info saved",Toast.LENGTH_LONG).show();
        }
        else {
            if(TextUtils.isEmpty(name)){
                e1.setError("Enter name");
            }
            if(TextUtils.isEmpty(s1)){
                e2.setError("Enter address");
            }
            if(TextUtils.isEmpty(s2)){
                e3.setError("Enter city");
            }

        }
    }
}
