package com.example.labrab55.dao;

import com.example.labrab55.entity.TaskStatus;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class TaskStatusDao extends BaseDao{
    public TaskStatusDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    @Override
    public void createTable() {
        jdbcTemplate.update("CREATE TABLE if not exists statuses( " +
                "id SERIAL PRIMARY KEY NOT NULL, " +
                "status VARCHAR(255) NOT NULL);");
    }
    public void saveStatuses(List<TaskStatus> statuses) {
        String sql = "INSERT INTO statuses (id, status) " +
                "VALUES (?, ?);";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,statuses.get(i).getId());
                ps.setString(2,statuses.get(i).getStatus());
                }
            @Override
            public int getBatchSize() {
                return statuses.size();
            }
        });
    }
    public void deleteAll() {
        String sql = "delete from statuses";
        jdbcTemplate.update(sql);
    }
    public void alertSequenceStatus() {
        String sql = "alter sequence statuses_id_seq restart with 1";
        jdbcTemplate.update(sql);
    }
}
