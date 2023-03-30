package com.example.labrab55.dto;

import com.example.labrab55.entity.Task;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TasksDto {
    private int id;
    private String title;
    private LocalDate dueDate;
    private String status;

    public static TasksDto from(Task task){
        return builder()
                .id(task.getId())
                .title(task.getTitle())
                .dueDate(task.getPlannedDate())
                .status(String.valueOf(task.getStatusId()))
                .build();
    }
}
