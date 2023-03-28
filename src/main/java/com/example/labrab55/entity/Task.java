package com.example.labrab55.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate plannedDate;
    private int userId;
    private TaskStatus status;

    public Task(int id, String title, String description, LocalDate plannedDate, int userId, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.plannedDate = plannedDate;
        this.userId = userId;
        this.status = status;
    }
}
