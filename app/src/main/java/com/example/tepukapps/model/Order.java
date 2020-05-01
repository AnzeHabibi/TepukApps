package com.example.tepukapps.model;

import java.util.List;

public class Order {
    private int id;
    private int user_id;
    private int pupuk_id;
    private int quantity;
    private String status;
    private String date;
    private List<Pupuk> pupuk;
    private List<User> user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPupuk_id() {
        return pupuk_id;
    }

    public void setPupuk_id(int pupuk_id) {
        this.pupuk_id = pupuk_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public List<Pupuk> getPupuk() {
        return pupuk;
    }

    public void setPupuk(List<Pupuk> pupuk) {
        this.pupuk = pupuk;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
