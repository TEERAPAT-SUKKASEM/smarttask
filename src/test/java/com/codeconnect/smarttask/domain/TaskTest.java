package com.codeconnect.smarttask.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the Task domain object.
 * Each test maps to an acceptance criterion (AC) from docs/01_SRS.md.
 */
class TaskTest {

    @Test
    @DisplayName("AC-1.1: a task stores the title, deadline, and importance it was created with")
    void taskStoresItsValues() {
        LocalDate tomorrow = LocalDate.of(2026, 7, 20);

        Task task = new Task("Finish report", tomorrow, 5);

        assertEquals("Finish report", task.title());
        assertEquals(tomorrow, task.deadline());
        assertEquals(5, task.importance());
    }

    @Test
    @DisplayName("AC-1.2: importance outside 1..5 is rejected")
    void importanceOutOfRangeIsRejected() {
        LocalDate someDay = LocalDate.of(2026, 7, 20);

        // Too high and too low must both be rejected.
        assertThrows(IllegalArgumentException.class,
                () -> new Task("Bad task", someDay, 6));
        assertThrows(IllegalArgumentException.class,
                () -> new Task("Bad task", someDay, 0));
    }
}
