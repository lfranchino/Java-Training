package com.scottlogic.javatraining.dao;
import com.scottlogic.javatraining.classes.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserJdbcDAO implements DAO <User>{

    private static final Logger log = LoggerFactory.getLogger(UserJdbcDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public UserJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> list() {
        String sql = "SELECT username, password FROM Users";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(User user) {

    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(User user, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
