package com.example.nouman.azzan;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;

public class OrderModel implements  Parcelable {
    public String uid;
    public String quantity;
    public String pid;
    public String oid;
    public Long created_at;
    public String productTitle;
    public String productPrice;
    public String productImage;

    public OrderModel()
    {}
    protected OrderModel(Parcel in) {
        quantity = in.readString();
        uid = in.readString();
        pid = in.readString();
        oid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quantity);
        dest.writeString(uid);
        dest.writeString(pid);
        dest.writeString(oid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
        }
    };


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String price) {
        this.quantity = price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String pid) {
        this.pid =oid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid =pid;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProducrTitle(String product) {
        this.productTitle =product;
    }


    public String getProductPrice() {
        return productPrice;
    }

    public void setProducrPrice(String product) {
        this.productPrice =product;
    }

}
