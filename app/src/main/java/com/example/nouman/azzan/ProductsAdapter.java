package com.example.nouman.azzan;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    public Context context;
    public ArrayList<ProductModel> productModels;
    public MyClick myClick;

    public ProductsAdapter(Context context, ArrayList<ProductModel> productModels,MyClick myClick)
    {
        this.context=context;
        this.productModels=productModels;
        this.myClick= myClick;

    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=   LayoutInflater.from(context).inflate(R.layout.row_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


        ProductModel item = productModels.get(position);
        holder.title.setText(item.title);
        holder.decs.setText(item.desc);
        holder.price.setText(String.valueOf(item.price));


        Glide.with(context).load(item.imageUrl).into(holder.image);
        holder.onMyClick(position);


    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder
    {

        CardView root;
        TextView title;
        TextView decs;
        ImageView image;
        TextView price;


        public ProductViewHolder(View itemView) {
            super(itemView);
            root=  itemView.findViewById(R.id.root);
            title= itemView.findViewById(R.id.title);
            decs= itemView.findViewById(R.id.desc);
            price= itemView.findViewById(R.id.price);

            image= itemView.findViewById(R.id.image);


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
