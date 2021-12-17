package com.scottlogic.javatraining;

public abstract class Order {

    String account;
    int price;
    int quantity;
    String action;

    public Order(String account, int price, int quantity, String action) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
    }

    @Override
    public String toString() {
        return "Order {" +
                "account: " + account +
                ", price: " + price +
                ", quantity: " + quantity +
                ", action: " + action +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Order)) {
            return false;
        }

        Order o = (Order) obj;

        return (o.account == this.account) && (o.price == this.price)
                && (o.quantity == this.quantity) && (o.action == this.action);
    }
}
