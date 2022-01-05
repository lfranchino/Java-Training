package com.scottlogic.javatraining.classes;

public class BuyAggregate extends AggregateOrder implements Comparable<BuyAggregate>{

    public BuyAggregate(int price, int quantity) {
        super(price, quantity);
    }

    @Override
    public int compareTo(BuyAggregate o) {
        return this.price - o.price;
    }
}
