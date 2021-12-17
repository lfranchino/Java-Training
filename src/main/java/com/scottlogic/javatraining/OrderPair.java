package com.scottlogic.javatraining;

public class OrderPair {

    BuyOrder buyOrder;
    SellOrder sellOrder;

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
