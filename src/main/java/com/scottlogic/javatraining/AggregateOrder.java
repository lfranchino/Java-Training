package com.scottlogic.javatraining;

public abstract class AggregateOrder {

    int price;
    int quantity;

    public AggregateOrder(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "AggregateOrder {" +
                "price: " + price +
                ", quantity: " + quantity +
                '}';
    }


}
