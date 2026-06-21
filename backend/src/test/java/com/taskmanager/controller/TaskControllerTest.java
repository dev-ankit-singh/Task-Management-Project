package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskControllerTest {


@Autowired
private TaskService taskService;

@Test
void getAllTasksTest() {

    List<Task> tasks = taskService.getAllTasks();

    assertNotNull(tasks);
    assertTrue(tasks.size() > 0);
}

@Test
void createTaskTest() {

    int before = taskService.getAllTasks().size();

    Task task = taskService.createTask(
            "My Task",
            "Sample Task"
    );

    int after = taskService.getAllTasks().size();

    assertNotNull(task);
    assertEquals(before + 1, after);
}

@Test
void updateTaskStatusTest() {

    Task task = taskService.createTask(
            "Test Task",
            "Testing"
    );

    Optional<Task> updated =
            taskService.updateTaskStatus(
                    task.getId(),
                    "Completed"
            );

    assertTrue(updated.isPresent());
    assertEquals(
            "Completed",
            updated.get().getStatus()
    );
}

@Test
void deleteTaskTest() {

    Task task = taskService.createTask(
            "Delete Task",
            "Delete Testing"
    );

    boolean deleted =
            taskService.deleteTask(task.getId());

    assertTrue(deleted);
}

@Test
void deleteInvalidTaskTest() {

    boolean deleted =
            taskService.deleteTask(1000L);

    assertFalse(deleted);
}


}
