package com.example.nouman.azzan;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements  Parcelable {

    public String desc;
    public String imageUrl;
    public String price;
    public String pid;

    public ProductModel()
    {}
    protected ProductModel(Parcel in) {
        desc = in.readString();
        imageUrl = in.readString();
        price = in.readString();
        title = in.readString();
        //uid = in.readString();
        pid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(desc);
        dest.writeString(imageUrl);
        dest.writeString(price);
        dest.writeString(title);
      //  dest.writeString(uid);
        dest.writeString(pid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }*/

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid =pid;
    }


    public String title;
   // public String uid;
}
