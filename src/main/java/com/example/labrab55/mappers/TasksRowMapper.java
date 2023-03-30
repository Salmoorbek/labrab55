package com.example.labrab55.mappers;

import com.example.labrab55.entity.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TasksRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Task.builder()
                .id(rs.getInt(1))
                .title(rs.getString(2))
                .statusId(rs.getInt(3))
                .plannedDate(rs.getDate(4).toLocalDate())
                .build();
    }
}
