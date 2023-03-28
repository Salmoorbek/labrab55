package com.example.labrab55.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate plannedDate;
    private int statusId;
    private int userId;

    public Task(int id, String title, String description, LocalDate plannedDate, int statusId, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.plannedDate = plannedDate;
        this.statusId = statusId;
        this.userId = userId;
    }
}
