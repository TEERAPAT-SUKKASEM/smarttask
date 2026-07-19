package com.codeconnect.smarttask.repository;

import com.codeconnect.smarttask.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The simplest possible TaskRepository: keeps tasks in a list in memory.
 * Good enough for the MVP demo; swappable for a database version later.
 */
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> findAll() {
        // Return a copy so callers can't modify our internal list by accident.
        return new ArrayList<>(tasks);
    }
}
