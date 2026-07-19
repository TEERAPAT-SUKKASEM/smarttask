package com.codeconnect.smarttask.service;

import com.codeconnect.smarttask.domain.Task;
import com.codeconnect.smarttask.engine.PriorityEngine;
import com.codeconnect.smarttask.engine.RuleBasedPriorityEngine;
import com.codeconnect.smarttask.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    private TaskService service;

    // @BeforeEach runs before EVERY test, giving each one a fresh, empty service.
    @BeforeEach
    void setUp() {
        service = new TaskService(new InMemoryTaskRepository(), new RuleBasedPriorityEngine());
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

    @Test
    @DisplayName("AC-4.1: the service works with ANY engine that implements the interface")
    void worksWithACustomEngine() {
        // A fake engine, written as a lambda, that simply reverses the tasks.
        // It proves the service doesn't care WHICH engine it's given — the whole point of Strategy.
        PriorityEngine reverseEngine = tasks -> {
            List<Task> copy = new ArrayList<>(tasks);
            Collections.reverse(copy);
            return copy;
        };
        TaskService custom = new TaskService(new InMemoryTaskRepository(), reverseEngine);
        custom.addTask(new Task("first", LocalDate.of(2026, 8, 1), 3));
        custom.addTask(new Task("second", LocalDate.of(2026, 8, 2), 3));

        List<Task> ordered = custom.prioritizedTasks();

        // The fake engine reversed them, so "second" is now first.
        assertEquals("second", ordered.get(0).title());
        assertEquals("first", ordered.get(1).title());
    }
}
