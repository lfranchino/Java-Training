package com.scottlogic.javatraining;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class APIController {

    Matcher matcher = new Matcher();

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, ArrayList<? extends Order>> getOrders() {
        HashMap<String, ArrayList<? extends Order>> orderMap = new HashMap<>();
        orderMap.put("buy", matcher.buyList);
        orderMap.put("sell", matcher.sellList);
        return orderMap;
    }

    @GetMapping("/pairedorders")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<OrderPair> getPairedOrders() {
        return matcher.pairedOrders;
    }

    @GetMapping("/aggregates")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, ArrayList<? extends AggregateOrder>> getAggregates() {
        HashMap<String, ArrayList<? extends AggregateOrder>> aggregateMap = new HashMap<>();
        aggregateMap.put("buy", matcher.buyAggregates);
        aggregateMap.put("sell", matcher.sellAggregates);
        return aggregateMap;
    }

    @PostMapping("/sellorder")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSellOrder(@RequestBody SellOrder order) {
        matcher.matchNewOrder(order);
    }

    @PostMapping("/buyorder")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBuyOrder(@RequestBody BuyOrder order) {
        matcher.matchNewOrder(order);
    }
}
