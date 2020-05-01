package com.example.tepukapps.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Order implements Parcelable {
    private int id;
    private int quantity;
    private int total;
    private int totalPayment;
    private String status;
    private String date;
    private Pupuk pupukOrder;


    public Order(){

    }

    protected Order(Parcel in) {
        id = in.readInt();
        quantity = in.readInt();
        total = in.readInt();
        totalPayment = in.readInt();
        status = in.readString();
        date = in.readString();
        pupukOrder = in.readParcelable(Pupuk.class.getClassLoader());
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Pupuk getPupukOrder() {
        return pupukOrder;
    }

    public void setPupukOrder(Pupuk pupukOrder) {
        this.pupukOrder = pupukOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(quantity);
        parcel.writeInt(total);
        parcel.writeInt(totalPayment);
        parcel.writeString(status);
        parcel.writeString(date);
        parcel.writeParcelable(pupukOrder, i);
    }
}
