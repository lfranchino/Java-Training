package com.scottlogic.javatraining;
import java.util.*;

public class Matcher {
    ArrayList<BuyOrder> buyList = new ArrayList<>();
    ArrayList<SellOrder> sellList = new ArrayList<>();
    ArrayList<OrderPair> pairedOrders = new ArrayList<>();
    ArrayList<BuyAggregate> buyAggregates = new ArrayList<>();
    ArrayList<SellAggregate> sellAggregates = new ArrayList<>();
    ArrayList<BuyAggregate> accumulativeBuys = new ArrayList<>();
    ArrayList<SellAggregate> accumulativeSells = new ArrayList<>();

    public void storeOrder(Order order) {
        if (order instanceof SellOrder) {
            sellList.add((SellOrder) order);
            Collections.sort(sellList);
            aggregate(sellList, "sell");
            accumulate("sell");
        }
        else {
            buyList.add((BuyOrder) order);
            Collections.sort(buyList);
            aggregate(buyList, "buy");
            accumulate("buy");
        }
    }

    public void matchNewOrder(Order order) {
        ArrayList<Order> emptyOrders = new ArrayList<>();
        if (order instanceof SellOrder) {
            for (BuyOrder item : buyList) {
                if (order.quantity == 0) {
                    break;
                }
                if (order.price <= item.price) {
                    makeTrade(item, (SellOrder) order);
                }
                if (item.quantity == 0) {
                    emptyOrders.add(0, item);
                }
            }
        }
        else {
            for (SellOrder item : sellList) {
                if (order.quantity == 0) {
                    break;
                }
                if (order.price >= item.price) {
                    makeTrade((BuyOrder) order, item);
                }
                if (item.quantity == 0) {
                    emptyOrders.add(0, item);
                }
            }
        }
        for (Order item : emptyOrders) {
            removeOrder(item);
        }
        if (order.quantity != 0) {
            if (order instanceof SellOrder) {
                storeOrder(order);
            }
            else {
                storeOrder(order);
            }
        }
    }

    public void makeTrade(BuyOrder buyOrder, SellOrder sellOrder) {
        BuyOrder buyCopy = new BuyOrder(buyOrder.account, buyOrder.price, buyOrder.quantity, buyOrder.action);
        SellOrder sellCopy = new SellOrder(sellOrder.account, sellOrder.price, sellOrder.quantity, sellOrder.action);
        pairedOrders.add(0 ,new OrderPair(buyCopy, sellCopy));
        int minTrade = Math.min(buyOrder.quantity, sellOrder.quantity);
        buyOrder.setQuantity(buyOrder.quantity - minTrade);
        sellOrder.setQuantity(sellOrder.quantity - minTrade);
    }


    public void removeOrder(Order order) {
        if (order instanceof SellOrder) {
            for (SellOrder o : sellList) {
                if (o.equals(order)) {
                    sellList.remove(o);
                    return;
                }
            }
        }
        else {
            for (BuyOrder o : buyList) {
                if (o.equals(order)) {
                    buyList.remove(o);
                    return;
                }
            }
        }
    }

    public void aggregate(List<? extends Order> list, String action) {
        HashMap<Integer, Integer> aggregateMap = new HashMap<>();
        for (Order item : list) {
            Integer mapValue = aggregateMap.get(item.price);
            if (mapValue == null) {
                aggregateMap.put(item.price, item.quantity);
            }
            else {
                mapValue += item.quantity;
                aggregateMap.put(item.price, mapValue);
            }
        }

        if (action.equals("sell")) {
            sellAggregates.clear();
            for (Map.Entry<Integer, Integer> entry : aggregateMap.entrySet()) {
                sellAggregates.add(new SellAggregate(entry.getKey(), entry.getValue()));
            }
            Collections.sort(sellAggregates);
        }
        else {
            buyAggregates.clear();
            for (Map.Entry<Integer, Integer> entry : aggregateMap.entrySet()) {
                buyAggregates.add(new BuyAggregate(entry.getKey(), entry.getValue()));
            }
            Collections.sort(buyAggregates);
        }
    }

    public void accumulate(String action) {
        int total = 0;
        if (action.equals("sell")) {
            accumulativeSells.clear();
            for (SellAggregate item : sellAggregates) {
                total += item.quantity;
                accumulativeSells.add(0, new SellAggregate(item.price, total));
            }
        }
        else {
            accumulativeBuys.clear();
            for (BuyAggregate item : buyAggregates) {
                total += item.quantity;
                accumulativeBuys.add(new BuyAggregate(item.price, total));
            }
        }
    }
}