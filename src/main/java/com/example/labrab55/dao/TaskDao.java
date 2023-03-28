package com.example.labrab55.dao;

import com.example.labrab55.entity.Task;
import com.example.labrab55.entity.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class TaskDao extends BaseDao {
    public TaskDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE if not exists tasks (\n" +
                "  id SERIAL PRIMARY KEY,\n" +
                "  title VARCHAR(255) NOT NULL,\n" +
                "  description TEXT,\n" +
                "  plannedDate DATE NOT NULL,\n" +
                "  status_id BIGINT NOT NULL,\n" +
                "  user_id BIGINT NOT NULL,\n" +
                "  FOREIGN KEY (status_id) REFERENCES statuses(id),\n" +
                "  FOREIGN KEY (user_id) REFERENCES users(id)\n" +
                ");");
    }
    public void deleteAll() {
        String sql = "delete from tasks";
        jdbcTemplate.update(sql);
    }
    public void saveTasks(List<Task> tasks){
        String sql = "INSERT INTO tasks (title, description, plannedDate, status_id,user_id)\n" +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,tasks.get(i).getTitle());
                ps.setString(2,tasks.get(i).getDescription());
                ps.setDate(3, Date.valueOf(tasks.get(i).getPlannedDate()));
                ps.setInt(4,tasks.get(i).getStatusId());
                ps.setInt(5,tasks.get(i).getUserId());
            }
            @Override
            public int getBatchSize() {
                return tasks.size();
            }
        });
    }
}
