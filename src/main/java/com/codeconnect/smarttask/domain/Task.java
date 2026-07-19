package com.codeconnect.smarttask.domain;

import java.time.LocalDate;

/**
 * A task the user wants to do.
 *
 * <p>This is a Java {@code record}: by writing the fields once in the header, Java automatically
 * generates the constructor, the accessors ({@code title()}, {@code deadline()}, {@code importance()}),
 * and {@code equals()}, {@code hashCode()}, {@code toString()}. Less boilerplate, fewer bugs.
 *
 * @param title      what the task is
 * @param deadline   when it is due
 * @param importance how important it is, from 1 (low) to 5 (high)
 */
public record Task(String title, LocalDate deadline, int importance) {

    /**
     * Compact constructor: runs automatically whenever a Task is created.
     * We use it to reject invalid data early, so a Task is always valid once it exists.
     */
    public Task {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
        if (deadline == null) {
            throw new IllegalArgumentException("deadline must not be null");
        }
        if (importance < 1 || importance > 5) {
            throw new IllegalArgumentException("importance must be between 1 and 5, but was " + importance);
        }
    }
}
