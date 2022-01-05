package com.scottlogic.javatraining.classes;

public class SellAggregate extends AggregateOrder implements Comparable<SellAggregate> {

    public SellAggregate(int price, int quantity) {
        super(price, quantity);
    }

    @Override
    public int compareTo(SellAggregate o) {
        return o.price - this.price;
    }
}

