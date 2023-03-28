package com.example.labrab55.dao;

import com.example.labrab55.entity.User;
import com.example.labrab55.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class UserDao extends BaseDao{
    @Autowired
    private final PasswordEncoder encoder;
    public UserDao(JdbcTemplate jdbcTemplate, PasswordEncoder encoder) {
        super(jdbcTemplate);
        this.encoder = encoder;
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE user (\n" +
                "  id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  email VARCHAR(255) NOT NULL,\n" +
                "  username VARCHAR(255) NOT NULL,\n" +
                "  password VARCHAR(255) NOT NULL\n" +
                ");");
    }
    public void createNewUser(User user){
        String sql = "INSERT INTO users (email, name, password)\n" +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, this.encoder.encode(user.getPassword()));
            return ps;
        });
    }
    public Optional<User> checkUser(String email, String password) throws ResourceNotFoundException {
        String sql = "SELECT id, email, name, password FROM users WHERE email = ? AND password = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[] { email, password },
                    (rs, rowNum) -> new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getString("password")
                    )
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Error getting user by email and password", e);
        }
    }
}
