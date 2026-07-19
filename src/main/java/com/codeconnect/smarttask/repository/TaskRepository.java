package com.codeconnect.smarttask.repository;

import com.codeconnect.smarttask.domain.Task;

import java.util.List;

/**
 * The Repository pattern: this interface hides WHERE tasks are stored.
 * Today it's an in-memory list; tomorrow it could be a database — and nothing
 * that depends on this interface would have to change.
 */
public interface TaskRepository {

    /** Store a task. */
    void add(Task task);

    /** Return all stored tasks. */
    List<Task> findAll();
}
