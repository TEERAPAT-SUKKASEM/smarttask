package com.codeconnect.smarttask.app;

import com.codeconnect.smarttask.domain.Task;
import com.codeconnect.smarttask.engine.RuleBasedPriorityEngine;
import com.codeconnect.smarttask.repository.InMemoryTaskRepository;
import com.codeconnect.smarttask.service.TaskService;

import java.time.LocalDate;
import java.util.List;

/**
 * The runnable entry point (the "composition root").
 *
 * <p>This is the ONLY place that chooses the concrete pieces — the in-memory repository and the
 * rule-based engine — and wires them into the service. Swapping to a database or an LLM engine
 * later means changing just these two lines.
 */
public class SmartTaskApp {

    public static void main(String[] args) {
        TaskService service = new TaskService(
                new InMemoryTaskRepository(),
                new RuleBasedPriorityEngine());

        // Sample tasks (in a full app these would come from the user).
        LocalDate today = LocalDate.now();
        service.addTask(new Task("Email the professor",  today.plusDays(7), 2));
        service.addTask(new Task("Finish SRS document",  today.plusDays(1), 5));
        service.addTask(new Task("Buy groceries",        today.plusDays(3), 1));
        service.addTask(new Task("Prepare pitch slides", today.plusDays(2), 4));

        System.out.println("=== SmartTask — recommended order ===");
        List<Task> ordered = service.prioritizedTasks();
        int rank = 1;
        for (Task t : ordered) {
            System.out.printf("%d. %-22s (due %s, importance %d)%n",
                    rank++, t.title(), t.deadline(), t.importance());
        }
    }
}
