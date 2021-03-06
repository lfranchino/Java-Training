package com.scottlogic.javatraining;


import com.scottlogic.javatraining.classes.*;
import com.scottlogic.javatraining.dao.DAO;
import com.scottlogic.javatraining.dao.UserJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
public class APIController {

    final int WORKLOAD_STRENGTH = 10;

    Matcher matcher = new Matcher();

    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder(WORKLOAD_STRENGTH, new SecureRandom());

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Autowired
    UserJdbcDAO UserDao = new UserJdbcDAO(jdbcTemplate);

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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<User> optionalUser = UserDao.get(username);
        if (optionalUser.isEmpty()) {
            System.out.println("User does not exist");
            return null;
        }
        if (!bCryptPasswordEncoder.matches(password, optionalUser.get().password)) {
            System.out.println("Username or password is incorrect");
            return null;
        }
        String token = getJWTToken(username, password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setToken(token);
        return user;
    }

    @PostMapping("/createuser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<User> optionalUser = UserDao.get(username);
        if (optionalUser.isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            UserDao.create(username, encodedPassword);
            return;
        }
        System.out.println("User already exists");
    }

    private String getJWTToken(String username, String password) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username + password)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
