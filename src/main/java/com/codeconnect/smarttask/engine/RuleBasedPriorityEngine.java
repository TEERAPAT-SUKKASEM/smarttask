package com.codeconnect.smarttask.engine;

import com.codeconnect.smarttask.domain.Task;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

/**
 * Our MVP "AI": a transparent, rule-based priority engine.
 *
 * <p>Score for each task:
 * <pre>
 *     score = importance * 20   +   urgencyBonus(deadline)
 * </pre>
 * where a sooner (or overdue) deadline gives a bigger urgency bonus. Tasks with a higher score are
 * recommended first. It's simple and deterministic — which is exactly what makes it easy to test.
 */
public class RuleBasedPriorityEngine implements PriorityEngine {

    private final Clock clock;

    /** Production use: read the real current date. */
    public RuleBasedPriorityEngine() {
        this(Clock.systemDefaultZone());
    }

    /** Test use: pass a fixed clock so "today" is predictable. */
    public RuleBasedPriorityEngine(Clock clock) {
        this.clock = clock;
    }

    @Override
    public List<Task> prioritize(List<Task> tasks) {
        LocalDate today = LocalDate.now(clock);
        return tasks.stream()
                .sorted(Comparator.comparingDouble((Task t) -> score(t, today)).reversed())
                .toList();
    }

    /** Higher score = should be done sooner. Package-private so tests can check it directly. */
    double score(Task task, LocalDate today) {
        long daysUntilDeadline = ChronoUnit.DAYS.between(today, task.deadline());
        double urgencyBonus = 100 - daysUntilDeadline;      // sooner or overdue -> larger
        double importanceWeight = task.importance() * 20;   // 1..5 -> 20..100
        return importanceWeight + urgencyBonus;
    }
}
