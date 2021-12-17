package com.scottlogic.javatraining;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class APIController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

//    @GetMapping("/Orders")
//    public Array getOrders() {
//        return List<List<Order>> orderArray = new
//    }
}
