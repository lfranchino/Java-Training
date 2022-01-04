package com.scottlogic.javatraining;

public abstract class AggregateOrder {

    int price;
    int quantity;

    public AggregateOrder(int price, int quantity) {
        this.price = price;
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
