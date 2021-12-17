package com.scottlogic.javatraining;

public class BuyOrder extends Order implements Comparable<BuyOrder> {

    public BuyOrder(String account, int price, int quantity, String action) {
        super(account, price, quantity, action);
    }

    @Override
    public int compareTo(BuyOrder o) {
        return this.price - o.price;
    }
}

