package com.scottlogic.javatraining.classes;

public class OrderPair {

    public BuyOrder buyOrder;
    public SellOrder sellOrder;

    public OrderPair(BuyOrder buyOrder, SellOrder sellOrder) {
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
    }

    @Override
    public String toString() {
        return "OrderPair {" +
                "buyOrder: " + buyOrder +
                ", sellOrder: " + sellOrder +
                '}';
    }
}
