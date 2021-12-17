package com.scottlogic.javatraining;

public class SellOrder extends Order implements Comparable<SellOrder> {

    public SellOrder(String account, int price, int quantity, String action) {
        super(account, price, quantity, action);
    }

    @Override
    public int compareTo(SellOrder o) {
        return o.price - this.price;
    }
}

