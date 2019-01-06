package com.example.nouman.azzan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class AllProductsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    ProductsAdapter productsAdapter;
    ArrayList<ProductModel> productModels;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);


        recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllProductsActivity.this, LinearLayout.VERTICAL,false));

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com//islamicproducts");

        productModels= new ArrayList<>();

        // Attach a listener to read the data at our posts reference
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    ProductModel productModel= new ProductModel();
                    productModel.title = (String) messageSnapshot.child("title").getValue();
                    productModel.desc = (String) messageSnapshot.child("desc").getValue();
                    productModel.price = (String) messageSnapshot.child("price").getValue();
                    productModel.imageUrl = (String) messageSnapshot.child("imageUrl").getValue();
                   // productModel.uid = (String) messageSnapshot.child("uid").getValue();
                    productModel.pid = messageSnapshot.getKey();
                    productModels.add(productModel);
                }

                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        System.out.println(productModels);

//        for (int  i=0; i<10;i++)
//        {
//            ProductModel productModel= new ProductModel();
//            productModel.title="title "+i;
//            productModel.desc="description "+i;
//            productModel.price=i;
//            productModel.imageUrl="https://2.bp.blogspot.com/-2ZMkSo7CnUs/WvMvSK0u9RI/AAAAAAAAFZA/zJOCZ8LUM8ol3hcHYHwVyOpc3iiYaxquACLcBGAs/s1600/Jetpack_logo.png";
//            productModels.add(productModel);
//
//        }

        productsAdapter= new ProductsAdapter(AllProductsActivity.this, productModels, new ProductsAdapter.MyClick() {
            @Override
            public void OnClick(int position) {

                Intent mIntent= new Intent(AllProductsActivity.this,ViewProduct.class);
                mIntent.putExtra("data",productModels.get(position));
                startActivity(mIntent);

            }
        });
        recyclerView.setAdapter(productsAdapter);
        productsAdapter.notifyDataSetChanged();


    }
}
