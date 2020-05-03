package com.example.tepukapps.model;

public class Payment {
    private int id;
    private String codePayment;
    private int ammountPayment;
    private Order order;
    private String datePayment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodePayment() {
        return codePayment;
    }

    public void setCodePayment(String codePayment) {
        this.codePayment = codePayment;
    }

    public int getAmmountPayment() {
        return ammountPayment;
    }

    public void setAmmountPayment(int ammountPayment) {
        this.ammountPayment = ammountPayment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }
}
