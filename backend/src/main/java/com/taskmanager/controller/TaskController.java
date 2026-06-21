package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskRequest;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class TaskController {


@Autowired
private TaskService taskService;

@GetMapping
public List<Task> getAllTasks() {
    return taskService.getAllTasks();
}

@PostMapping
public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest request) {

    Task task = taskService.createTask(
            request.getName(),
            request.getDescription()
    );

    return ResponseEntity.ok(task);
}

@PatchMapping("/{id}/status")
public ResponseEntity<?> updateStatus(
        @PathVariable Long id,
        @RequestBody Map<String, String> data) {

    String status = data.get("status");

    return taskService.updateTaskStatus(id, status)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteTask(@PathVariable Long id) {

    boolean deleted = taskService.deleteTask(id);

    if (deleted) {
        return ResponseEntity.ok("Task Deleted");
    }

    return ResponseEntity.notFound().build();
}


}
