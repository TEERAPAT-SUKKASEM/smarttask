package com.codeconnect.smarttask.service;

import com.codeconnect.smarttask.domain.Task;
import com.codeconnect.smarttask.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    private TaskService service;

    // @BeforeEach runs before EVERY test, giving each one a fresh, empty service.
    @BeforeEach
    void setUp() {
        service = new TaskService(new InMemoryTaskRepository());
    }

    @Test
    @DisplayName("a brand-new service has no tasks")
    void startsEmpty() {
        assertTrue(service.allTasks().isEmpty());
    }

    @Test
    @DisplayName("AC-2.1: after adding 3 tasks, all 3 are returned")
    void listsAllAddedTasks() {
        service.addTask(new Task("A", LocalDate.of(2026, 8, 1), 3));
        service.addTask(new Task("B", LocalDate.of(2026, 8, 2), 2));
        service.addTask(new Task("C", LocalDate.of(2026, 8, 3), 1));

        List<Task> tasks = service.allTasks();

        assertEquals(3, tasks.size());
    }
}
