# Software Requirements Specification (SRS) — SmartTask

| Field | Value |
|-------|-------|
| **Project** | SmartTask — AI-Assisted Task Prioritizer |
| **Version** | 1.0 (MVP) |
| **Date** | 2026-07-18 |
| **Author** | TEERAPAT SUKKASEM |

> 💡 **What an SRS is:** a clear, agreed statement of *what* the software must do (not *how* it's
> coded). It is the contract between the team and the judges. On Day 2 you write your tests
> *directly from this document* — so every requirement here must be **specific and testable**.

---

## 1. Purpose & Scope

**Purpose.** Students juggle many tasks and waste time deciding what to do first. SmartTask takes a
list of tasks and uses a pluggable AI priority engine to recommend the best order to complete them.

**In scope (MVP):**
- Add a task with a title, deadline, and importance level.
- List all tasks.
- Get tasks back in prioritized order (highest priority first).
- A rule-based priority engine, designed so a smarter (LLM-based) engine can replace it later.

**Out of scope (for the MVP):**
- User accounts / login.
- Saving tasks to a database (tasks live in memory for the MVP).
- A real Large Language Model integration (this is a documented *future* extension).

---

## 2. Users & Stakeholders

| Actor | Goal |
|-------|------|
| **Student (primary user)** | Add tasks and receive a recommended order of work. |
| **Judges / committee** | Verify the system meets the SRS via a live demo and tests. |

---

## 3. Functional Requirements

> Each requirement has an ID (so tests can reference it) and **acceptance criteria** written in
> **Given / When / Then** form. This BDD style is what you'll turn straight into test cases on Day 2.

### FR-1 — Add a task
The system shall let a user add a task with a **title** (text), a **deadline** (a date), and an
**importance** (integer 1–5, where 5 is most important).
- **AC-1.1** — *Given* an empty task list, *When* a user adds a task "Finish report" (deadline
  tomorrow, importance 5), *Then* the task list contains exactly 1 task with those values.
- **AC-1.2** — *Given* an importance value outside 1–5, *When* the user adds the task, *Then* the
  system rejects it with a clear error.

### FR-2 — List tasks
The system shall return all tasks that have been added.
- **AC-2.1** — *Given* 3 tasks were added, *When* the user lists tasks, *Then* all 3 are returned.

### FR-3 — Prioritize tasks
The system shall return the tasks ordered by a computed **priority score**, highest first.
The score combines **urgency** (how soon the deadline is) and **importance**.
- **AC-3.1** — *Given* two tasks with equal importance, *When* prioritized, *Then* the one with the
  **earlier deadline** comes first.
- **AC-3.2** — *Given* two tasks with the same deadline, *When* prioritized, *Then* the one with the
  **higher importance** comes first.
- **AC-3.3** — *Given* an empty task list, *When* prioritized, *Then* an empty list is returned (no error).

### FR-4 — Pluggable priority engine
The priority engine shall be replaceable without changing the code that calls it. The MVP ships a
**rule-based engine**; the design must allow an **LLM-based engine** to be added later.
- **AC-4.1** — *Given* the system is configured with a different engine implementing the same
  interface, *When* prioritization runs, *Then* it works with no change to the calling code.

---

## 4. Non-Functional Requirements

| ID | Requirement | How it's verified |
|----|-------------|-------------------|
| **NFR-1 (Performance)** | Prioritizing up to 100 tasks completes in under 200 ms. | Timed test. |
| **NFR-2 (Testability)** | Core logic covered by automated unit tests. | Test suite + coverage. |
| **NFR-3 (Extensibility)** | New priority engines added without editing existing engines/callers. | Code review of interface. |
| **NFR-4 (Deployability)** | The app runs from a single command / single packaged artifact. | Live demo on Day 3. |

---

## 5. Constraints & Assumptions
- **Constraints:** Java 25; source managed in Git from the start; built with Maven.
- **Assumptions:** Single-user, single-session use for the MVP; deadlines are valid dates.

---

## 6. Acceptance (Definition of Done for the MVP)
The MVP is "done" when **all FR acceptance criteria pass as automated tests**, the app runs from one
command, and the CI pipeline is green.
