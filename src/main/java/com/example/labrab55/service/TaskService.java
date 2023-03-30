package com.example.labrab55.service;

import com.example.labrab55.dao.TaskDao;
import com.example.labrab55.dto.TaskDto;
import com.example.labrab55.dto.TasksDto;
import com.example.labrab55.entity.Task;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDto createTask(Authentication authentication, String title, String descr, LocalDate date) {
        User user = (User) authentication.getPrincipal();
        int id = taskDao.findUserByUserEmail(user.getUsername());
        var newTask = Task.builder()
                .title(title)
                .description(descr)
                .plannedDate(date)
                .statusId(1)
                .userId(id)
                .build();
        taskDao.createTask(newTask);
        return TaskDto.from(newTask);
    }

    public List<TasksDto> getUserTasks(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<TasksDto> taskDtos = new ArrayList<>();
        for (int i = 0; i < taskDao.findAllByUserEmail(user.getUsername()).size(); i++) {
            taskDtos.add(TasksDto.from(taskDao.findAllByUserEmail(user.getUsername()).get(i)));
        }
        return taskDtos;
    }
    public TaskDto getTaskById(int taskId, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return TaskDto.from(taskDao.getTaskById(taskId, user.getUsername()));
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Incorrect id");
        }

    }
    public boolean updateTaskStatus(int taskId, int newStatus, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Task task = taskDao.getTaskByIdAndUserEmail(taskId, user.getUsername());
        if (task == null) {
            throw new RuntimeException("Задача не найдена");
        }
        if (newStatus == 2 && task.getStatusId() != 1) {
            throw new RuntimeException("Неверный статус для перехода в 'В работе'");
        }
        if (newStatus == 3 && task.getStatusId() != 2) {
            throw new RuntimeException("Неверный статус для перехода в 'Закрыта'");
        }
        task.setStatusId(newStatus);
        taskDao.updateTaskStatus(taskId, newStatus);
        return true;
    }
}
