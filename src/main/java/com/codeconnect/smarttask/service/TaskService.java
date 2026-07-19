package com.codeconnect.smarttask.service;

import com.codeconnect.smarttask.domain.Task;
import com.codeconnect.smarttask.engine.PriorityEngine;
import com.codeconnect.smarttask.repository.TaskRepository;

import java.util.List;

/**
 * The application layer: it coordinates the use cases (add a task, list tasks).
 *
 * <p>Notice it depends on the {@link TaskRepository} <em>interface</em>, not a concrete class.
 * This is <b>Dependency Injection</b>: we hand the service its repository from outside, so in
 * tests we can give it a simple in-memory one, and in production a database one — same service code.
 */
public class TaskService {

    private final TaskRepository repository;
    private final PriorityEngine engine;

    public TaskService(TaskRepository repository, PriorityEngine engine) {
        this.repository = repository;
        this.engine = engine;
    }

    /** Use case: add a task. */
    public void addTask(Task task) {
        repository.add(task);
    }

    /** Use case: list every task (in the order they were added). */
    public List<Task> allTasks() {
        return repository.findAll();
    }

    /** Use case: list tasks in recommended priority order, using whichever engine was injected. */
    public List<Task> prioritizedTasks() {
        return engine.prioritize(repository.findAll());
    }
}
