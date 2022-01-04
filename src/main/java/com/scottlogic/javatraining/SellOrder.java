package com.scottlogic.javatraining;

public class SellOrder extends Order implements Comparable<SellOrder> {

    public String action;

    public SellOrder(String account, int price, int quantity) {
        super(account, price, quantity);
        this.action = "sell";
    }

    @Override
    public int compareTo(SellOrder o) {
        return o.price - this.price;
    }

    @Override
    public String toString() {
        return "Order { " +
                "account: " + account +
                ", price: " + price +
                ", quantity: " + quantity +
                ", action: " + action + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SellOrder)) {
            return false;
        }

        SellOrder o = (SellOrder) obj;

        return (o.account == this.account) && (o.price == this.price)
                && (o.quantity == this.quantity);
    }
}

