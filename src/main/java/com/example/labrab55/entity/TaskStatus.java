package com.example.labrab55.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskStatus {
    private int id;
    private String status;

    public TaskStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }
}
