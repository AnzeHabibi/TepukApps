package com.example.tepukapps.model;

public class History {
    private int id;
    private int quantity;
    private int total;
    private int totalPayment;
    private String status;
    private String date;
    private Pupuk pupukOrder;

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
}
