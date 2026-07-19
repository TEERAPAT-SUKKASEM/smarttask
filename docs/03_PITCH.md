# Final Pitch & Demo Guide — SmartTask

> The Day 3 "Final Pitch" asks you to present **a live demo of actual usage, architecture, and
> testing** to the committee. This is your script. Aim for **~3–5 minutes**, then Q&A.

---

## The 60-second story (memorize this shape)

1. **Problem** — "Students juggle many tasks and waste time deciding what to do first."
2. **Solution** — "SmartTask ranks your tasks by urgency *and* importance, instantly."
3. **The twist** — "The ranking engine is pluggable: a transparent rule-based engine today, a
   real LLM tomorrow — with zero changes to the rest of the app."

---

## Pitch structure (what to say, in order)

| # | Beat | What to show / say | Why judges care |
|---|------|--------------------|-----------------|
| 1 | **Problem & solution** (30s) | The story above. | Shows you solved a real need. |
| 2 | **Architecture** (60s) | Open `docs/02_ARCHITECTURE.md` — point at the layered diagram and the **Strategy** class diagram. Say: "Nothing depends on a concrete engine; everything depends on the `PriorityEngine` interface." | Directly rewards Workshop 1: *design patterns for AI use cases*. |
| 3 | **Testing** (45s) | Run the tests live (command below). Say: "Every requirement in our SRS maps to a test — 8 tests, all green. We wrote them first, TDD-style." | Rewards Day 2: *shift-left testing*. |
| 4 | **CI/CD** (30s) | Show the green ✅ on the GitHub **Actions** tab. Say: "Every push runs these tests automatically and builds the artifact." | Rewards Workshop 3: *CI/CD*. |
| 5 | **Live demo** (45s) | Run the app (command below). Walk through why the order makes sense. | Proves it actually works. |
| 6 | **Roadmap** (15s) | "Next: an `LlmPriorityEngine` and a database — both already designed for, thanks to our interfaces." | Shows the design has a future. |

---

## Live demo script (rehearse these exact commands)

```bash
# 1. Show the tests all pass (shift-left / TDD proof)
./mvnw -q test

# 2. Build the deployable artifact
./mvnw -q clean package

# 3. Run the deployed app
java -jar target/smarttask-0.1.0-SNAPSHOT.jar
```

Expected demo output:
```
=== SmartTask - recommended order ===
1. Finish SRS document    (due ..., importance 5)
2. Prepare pitch slides   (due ..., importance 4)
3. Email the professor    (due ..., importance 2)
4. Buy groceries          (due ..., importance 1)
```

**The one line that wins points:** *"Notice 'Email the professor' outranks 'Buy groceries' even
though it's due later — because our engine blends importance with urgency, not just the deadline."*

---

## Anticipated judge questions (have answers ready)

- **"How would you add a real AI model?"** → "Write a class implementing `PriorityEngine` that calls
  the model, then change one line in `SmartTaskApp`. Nothing else changes — that's why we used the
  Strategy pattern."
- **"How do you know it works?"** → "Every SRS acceptance criterion is an automated test, and CI runs
  them on every commit. Green pipeline = requirements met."
- **"What did you cut for the MVP, and why is that OK?"** → "Database persistence and login. The
  Repository pattern means adding a database later touches only one class."
- **"What was the hardest part?"** → (pick a real one, e.g.) "Making time-based logic testable — we
  solved it by injecting a fixed `Clock` so tests are deterministic."

---

## Pre-pitch checklist
- [ ] `./mvnw -q test` is green.
- [ ] The jar runs.
- [ ] GitHub Actions shows a green checkmark.
- [ ] Architecture diagram open in a browser tab (renders on GitHub).
- [ ] You can explain the Strategy pattern in one sentence without notes.
