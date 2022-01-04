package com.scottlogic.javatraining;

import java.util.ArrayList;

public class OrdersList {
    public ArrayList<SellOrder> SellList;
    public ArrayList<BuyOrder> BuyList;

    public OrdersList(ArrayList<SellOrder> sellList, ArrayList<BuyOrder> buyList) {
        SellList = sellList;
        BuyList = buyList;
    }

    @Override
    public String toString() {
        return "OrdersList{" +
                "SellList=" + SellList +
                ", BuyList=" + BuyList +
                '}';
    }
}
