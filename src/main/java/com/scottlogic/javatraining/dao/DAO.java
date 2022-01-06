package com.scottlogic.javatraining.dao;

import com.scottlogic.javatraining.classes.User;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> list();

    void create(String username, String password);


    Optional<User> get(String username);

}
