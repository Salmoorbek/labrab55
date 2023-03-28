package com.example.labrab55.dto;

import com.example.labrab55.entity.Task;
import com.example.labrab55.entity.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDto {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;

    public static TaskDto from(Task task){
        return builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getPlannedDate())
                .status(task.getStatus())
                .build();
    }
}
