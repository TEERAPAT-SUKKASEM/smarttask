package com.codeconnect.smarttask.engine;

import com.codeconnect.smarttask.domain.Task;

import java.util.List;

/**
 * The "AI" contract (the Strategy pattern).
 *
 * <p>Any engine that can take a list of tasks and return them in a recommended order can plug in
 * here — a simple rule-based one today, an LLM-powered one tomorrow — and the rest of the app
 * never has to change.
 *
 * <p>It has a single method, which makes it a <b>functional interface</b>: you can even implement
 * it with a lambda (we do exactly that in a test to prove how swappable it is).
 */
@FunctionalInterface
public interface PriorityEngine {

    /**
     * Return the tasks ordered by priority, highest priority first.
     * The input list is not modified.
     */
    List<Task> prioritize(List<Task> tasks);
}
