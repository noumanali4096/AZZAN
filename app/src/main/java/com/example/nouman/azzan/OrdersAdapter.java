package com.example.nouman.azzan;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    public Context context;
    public ArrayList<OrderModel> orderModels;
    public MyClick myClick;
    public OrderModel item;
    public ProductModel product;
    public OrderViewHolder holder;

    public OrdersAdapter(Context context, ArrayList<OrderModel> orderModels, MyClick myClick)
    {
        this.context=context;
        this.orderModels=orderModels;
        this.myClick= myClick;

    }
    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=   LayoutInflater.from(context).inflate(R.layout.row_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {



        item = orderModels.get(position);

        holder.quantity.setText(String.valueOf(item.quantity));
        holder.title.setText(String.valueOf(item.productTitle));
        holder.price.setText(String.valueOf(item.productPrice));
        Glide.with(context).load(item.productImage).into(holder.productImage);

//        holder.created_at.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(item.created_at));

        //holder.onMyClick(position);


    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder
    {

        CardView root;
        TextView quantity;
        TextView title;
        TextView price;
        TextView created_at;
        ImageView productImage;


        public OrderViewHolder(View itemView) {
            super(itemView);
            root=  itemView.findViewById(R.id.root_order);
            quantity= itemView.findViewById(R.id.quantity);
            title= itemView.findViewById(R.id.title);
            price= itemView.findViewById(R.id.price);
            productImage= itemView.findViewById(R.id.productImage);
            created_at= itemView.findViewById(R.id.date);


        }
        public void onMyClick(final int position)
        {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClick.OnClick(position);

                }
            });

        }
    }

    public interface MyClick
    {
        void OnClick(int position);
    }
}
