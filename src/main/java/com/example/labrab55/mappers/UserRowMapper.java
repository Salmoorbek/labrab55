package com.example.labrab55.mappers;

import com.example.labrab55.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .email(rs.getString(3))
                .password(rs.getString(4))
                .build();
    }
}
