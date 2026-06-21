package com.taskmanager.model;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private Long id;

    @NotBlank(message = "Task name is required")
    private String name;

    private String description;

    private String status = "Pending";

    private String createdDate;

    public Task() {
        this.createdDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Task(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = "Pending";

        createdDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long taskId) {
        this.id = taskId;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String taskName) {
        this.name = taskName;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String taskDescription) {
        this.description = taskDescription;
    }

    // Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String taskStatus) {
        this.status = taskStatus;
    }

    // Created Date
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String date) {
        this.createdDate = date;
    }
}