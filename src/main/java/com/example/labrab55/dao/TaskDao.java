package com.example.labrab55.dao;

import com.example.labrab55.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class TaskDao extends BaseDao{
    public TaskDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE task (\n" +
                "  id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  title VARCHAR(255) NOT NULL,\n" +
                "  description TEXT,\n" +
                "  due_date DATE NOT NULL,\n" +
                "  status ENUM('NEW', 'IN_PROGRESS', 'CLOSED') NOT NULL,\n" +
                "  user_id BIGINT NOT NULL,\n" +
                "  FOREIGN KEY (user_id) REFERENCES user(id)\n" +
                ");");
    }

}
