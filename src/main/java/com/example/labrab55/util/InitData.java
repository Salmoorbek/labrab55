package com.example.labrab55.util;

import com.example.labrab55.dao.TaskDao;
import com.example.labrab55.dao.TaskStatusDao;
import com.example.labrab55.dao.UserDao;
import com.example.labrab55.entity.Task;
import com.example.labrab55.entity.TaskStatus;
import com.example.labrab55.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitData {
    @Bean
    CommandLineRunner init(UserDao userDao, TaskDao taskDao, TaskStatusDao statusDao) {
        return (args) -> {
            userDao.createTable();
            statusDao.createTable();
            taskDao.createTable();

            taskDao.deleteAll();
            userDao.deleteAll();
            statusDao.deleteAll();

            userDao.alertSequenceUser();
            taskDao.alertSequenceTask();
            statusDao.alertSequenceStatus();

            userDao.saveUser(creatUsers());
            statusDao.saveStatuses(creatTaskStatuses());
            taskDao.saveTasks(creatTasks());
        };
    }

    public List<User> creatUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"salmor","a@a.a","123",true,"USER"));
        users.add(new User(2,"ss","s@s.s","123",true,"USER"));
        return users;
    }
    public List<Task> creatTasks(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1,"Купить машину","qwertyqwertyqwerty", LocalDate.of(2022,3,5),1, 1));
        tasks.add(new Task(2,"Продать дом","qwertyqwertyqwerty", LocalDate.of(2022,3,5),2, 2));
        tasks.add(new Task(2,"Гулянка с друзьями","qwertyqwertyqwerty", LocalDate.of(2022,3,5),3, 1));
        return tasks;
    }
    public List<TaskStatus> creatTaskStatuses(){
        List<TaskStatus> statuses = new ArrayList<>();
        statuses.add(new TaskStatus(1,"Новая"));
        statuses.add(new TaskStatus(2,"В работе"));
        statuses.add(new TaskStatus(3,"Завершена"));
        return statuses;
    }
}
