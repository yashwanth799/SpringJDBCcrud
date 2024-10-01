package com.ivoyant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, email);
        System.out.println("Row inserted Successfully");

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users=jdbcTemplate.query(sql,new RowMapperImplementation());
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> rowMapper=new RowMapperImplementation();
        User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return user;
    }

    public void updateUserEmail(int id, String newEmail) {
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        jdbcTemplate.update(sql, newEmail, id);
    }

    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}