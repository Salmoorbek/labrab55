package com.example.labrab55.controller;

import com.example.labrab55.dto.TaskDto;
import com.example.labrab55.dto.TasksDto;
import com.example.labrab55.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public ResponseEntity<List<TasksDto>> getTasksForAuthenticatedUser(Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
        List<TasksDto> tasksForAuthenticatedUser = taskService.getUserTasks(authentication);
        if(tasksForAuthenticatedUser!=null)
            return new ResponseEntity<>(tasksForAuthenticatedUser, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestParam String title,
                                              @RequestParam String descr,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                              Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
        TaskDto createdTaskDto = taskService.createTask(authentication, title, descr, date);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Void> updateTaskStatus(@PathVariable int taskId, @RequestParam int newStatus, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
        if(taskService.updateTaskStatus(taskId, newStatus , authentication))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskByIdForAuthenticatedUser(@PathVariable int taskId, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
        TaskDto taskByIdForAuthenticatedUser = taskService.getTaskById(taskId, authentication);
        if(taskByIdForAuthenticatedUser!=null)
            return new ResponseEntity<>(taskByIdForAuthenticatedUser, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
