package com.scottlogic.javatraining.classes;

public class BuyOrder extends Order implements Comparable<BuyOrder> {

    public String action;

    public BuyOrder(String account, int price, int quantity) {
        super(account, price, quantity);
        this.action = "buy";
    }

    @Override
    public int compareTo(BuyOrder o) {
        return this.price - o.price;
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

        if (!(obj instanceof BuyOrder)) {
            return false;
        }

        BuyOrder o = (BuyOrder) obj;

        return (o.account == this.account) && (o.price == this.price)
                && (o.quantity == this.quantity);
    }
}

