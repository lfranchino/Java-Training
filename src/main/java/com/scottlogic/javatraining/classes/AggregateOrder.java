package com.scottlogic.javatraining.classes;

public abstract class AggregateOrder {

    public int price;
    public int quantity;

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
