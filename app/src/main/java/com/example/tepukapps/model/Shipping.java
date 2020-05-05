package com.example.tepukapps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Shipping implements Parcelable {
    private int id;
    private String name;
    private String detail;
    private int photo;
    private int estimate;
    private String kurir;
    private String status;
    private String date;
    private Payment payment;
    private User user;
    private Pupuk pupuk;
    private Order order;

    public Shipping() {
    }

    protected Shipping(Parcel in) {
        id = in.readInt();
        name = in.readString();
        detail = in.readString();
        photo = in.readInt();
        estimate = in.readInt();
        kurir = in.readString();
        status = in.readString();
        date = in.readString();
        pupuk = in.readParcelable(Pupuk.class.getClassLoader());
        order = in.readParcelable(Order.class.getClassLoader());
    }

    public static final Creator<Shipping> CREATOR = new Creator<Shipping>() {
        @Override
        public Shipping createFromParcel(Parcel in) {
            return new Shipping(in);
        }

        @Override
        public Shipping[] newArray(int size) {
            return new Shipping[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pupuk getPupuk() {
        return pupuk;
    }

    public void setPupuk(Pupuk pupuk) {
        this.pupuk = pupuk;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(detail);
        parcel.writeInt(photo);
        parcel.writeInt(estimate);
        parcel.writeString(kurir);
        parcel.writeString(status);
        parcel.writeString(date);
        parcel.writeParcelable(pupuk, i);
        parcel.writeParcelable(order, i);
    }
}
