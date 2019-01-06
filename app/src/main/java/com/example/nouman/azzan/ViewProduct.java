package com.example.nouman.azzan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Date;
public class ViewProduct extends AppCompatActivity {

    TextView title, desc, price,edit,delete;
    private ImageView image;
    private DatabaseReference mDatabase;
    private FirebaseStorage mFirebaseStorage;
    Button purchase_btn;
    int quantity = 0;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference orderDatabase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_product);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/islamicproducts");
        //orderDatabase = mDatabase = FirebaseDatabase.getInstance().getReference().child("orders");
        orderDatabase=FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/orders");
        mFirebaseStorage = FirebaseStorage.getInstance();

        final ProductModel productModel = getIntent().getParcelableExtra("data");
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        price = findViewById(R.id.price);
        image=findViewById(R.id.image);

        purchase_btn=findViewById(R.id.purchase_btn);
        purchase_btn.setEnabled(false);
        title.setText(productModel.title);
        desc.setText(productModel.desc);
        price.setText(String.valueOf(productModel.price));
        Glide.with(this).load(productModel.imageUrl).into(image);

        purchase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference newOrder = orderDatabase.push();

                String id = newOrder.getKey();

                //adding post contents to database reference
                orderDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        newOrder.child("quantity").setValue(Integer.toString(quantity));
                        newOrder.child("pid").setValue(productModel.pid);
                        newOrder.child("uid").setValue(mCurrentUser.getUid());
                        newOrder.child("created_at").setValue( new Date().getTime())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){

                                            finish();
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(ViewProduct.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void increaseInteger(View view) {
        quantity = quantity + 1;
        display(quantity);
        if(quantity > 0){
            purchase_btn.setEnabled(true);
        }
        else
            purchase_btn.setEnabled(false);

    }public void decreaseInteger(View view) {
        if(quantity>0)
            quantity = quantity - 1;
        display(quantity);
        if(quantity > 0){
            purchase_btn.setEnabled(true);
        }
        else
            purchase_btn.setEnabled(false);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.quantity);
        displayInteger.setText("" + number);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:

                onBackPressed();

                break;
        }

        return true;
    }
}
