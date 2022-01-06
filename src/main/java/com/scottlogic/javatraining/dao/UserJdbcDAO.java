package com.scottlogic.javatraining.dao;
import com.scottlogic.javatraining.classes.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public void create(String username, String password) {
        String sql = "INSERT INTO Users(username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, password);
    }

    @Override
    public Optional<User> get(String username) {
        String sql = String.format("SELECT * FROM Users WHERE username = '%s'", username);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper);
        } catch (DataAccessException e) {
            System.out.println("User not found.");
        }
        return Optional.ofNullable(user);
    }

}
