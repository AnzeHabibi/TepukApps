package com.example.tepukapps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pupuk implements Parcelable {
    private int id;
    private String name;
    private String category;
    private String description;
    private String composition;
    private int price;
    private String photo;

    public Pupuk() {

    }

    protected Pupuk(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readString();
        description = in.readString();
        composition = in.readString();
        price = in.readInt();
        photo = in.readString();
    }

    public static final Creator<Pupuk> CREATOR = new Creator<Pupuk>() {
        @Override
        public Pupuk createFromParcel(Parcel in) {
            return new Pupuk(in);
        }

        @Override
        public Pupuk[] newArray(int size) {
            return new Pupuk[size];
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(category);
        parcel.writeString(description);
        parcel.writeString(composition);
        parcel.writeInt(price);
        parcel.writeString(photo);
    }
}
