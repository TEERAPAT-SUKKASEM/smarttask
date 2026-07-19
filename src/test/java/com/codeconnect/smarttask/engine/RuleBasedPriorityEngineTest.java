package com.codeconnect.smarttask.engine;

import com.codeconnect.smarttask.domain.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuleBasedPriorityEngineTest {

    // Pin "today" so urgency is deterministic — the test gives the same result forever.
    private static final LocalDate TODAY = LocalDate.of(2026, 7, 20);

    private final PriorityEngine engine = new RuleBasedPriorityEngine(
            Clock.fixed(TODAY.atStartOfDay(ZoneOffset.UTC).toInstant(), ZoneOffset.UTC));

    @Test
    @DisplayName("AC-3.1: with equal importance, the earlier deadline comes first")
    void earlierDeadlineFirstWhenImportanceEqual() {
        Task later   = new Task("Later",   TODAY.plusDays(10), 3);
        Task earlier = new Task("Earlier", TODAY.plusDays(2),  3);

        List<Task> ordered = engine.prioritize(List.of(later, earlier));

        assertEquals("Earlier", ordered.get(0).title());
        assertEquals("Later",   ordered.get(1).title());
    }

    @Test
    @DisplayName("AC-3.2: with the same deadline, higher importance comes first")
    void higherImportanceFirstWhenDeadlineEqual() {
        Task low  = new Task("Low",  TODAY.plusDays(5), 2);
        Task high = new Task("High", TODAY.plusDays(5), 5);

        List<Task> ordered = engine.prioritize(List.of(low, high));

        assertEquals("High", ordered.get(0).title());
        assertEquals("Low",  ordered.get(1).title());
    }

    @Test
    @DisplayName("AC-3.3: prioritizing an empty list returns an empty list (no error)")
    void emptyListReturnsEmpty() {
        List<Task> ordered = engine.prioritize(List.of());

        assertTrue(ordered.isEmpty());
    }
}
