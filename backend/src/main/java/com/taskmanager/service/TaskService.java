package com.taskmanager.service;

import com.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> taskList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Loading some sample tasks when application starts
    public TaskService() {
        Task task1 = new Task(idCounter.getAndIncrement(), "Design Database Schema", "Create the ER diagram and define all tables needed for the project");
        task1.setStatus("Completed");

        Task task2 = new Task(idCounter.getAndIncrement(), "Setup Spring Boot Project", "Initialize the project with required dependencies and configuration");
        task2.setStatus("Completed");

        Task task3 = new Task(idCounter.getAndIncrement(), "Build REST APIs", "Develop all CRUD endpoints for task management");
        task3.setStatus("Pending");

        Task task4 = new Task(idCounter.getAndIncrement(), "Create Angular Frontend", "Build the UI components and integrate with backend APIs");
        task4.setStatus("Pending");

        Task task5 = new Task(idCounter.getAndIncrement(), "Write Unit Tests", "Add test cases for service and controller layers");
        task5.setStatus("Pending");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public Task createTask(String name, String description) {
        Task newTask = new Task(idCounter.getAndIncrement(), name, description);
        taskList.add(newTask);
        return newTask;
    }

    public Optional<Task> updateTaskStatus(Long id, String status) {
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                task.setStatus(status);
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }

    public boolean deleteTask(Long id) {
        return taskList.removeIf(task -> task.getId().equals(id));
    }

    public Optional<Task> findById(Long id) {
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }
}
