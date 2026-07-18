# SmartTask — AI-Assisted Task Prioritizer

> A practice project for the **SE Code Connect** competition (31 Jul – 2 Aug 2026, Mae Fah Luang University).
> Built as a full-SDLC dry run: **SRS → Architecture → TDD → CI/CD → Deploy → Pitch.**

## What it does
You add tasks (title, deadline, importance). A pluggable **AI priority engine** computes a
priority score for each task and returns them in the best order to tackle them.

The priority engine sits behind an interface (the **Strategy** design pattern), so we can start
with a simple **rule-based engine** and later swap in an **LLM-based engine** — without changing
any other code. This is the standard way to build AI features that stay testable and vendor-neutral.

## Tech stack
- **Language:** Java 25
- **Build tool:** Maven (added in Phase 2)
- **Testing:** JUnit 5 (added in Phase 2)
- **CI/CD:** GitHub Actions (added in Phase 3)

## Project docs
| Document | What it is |
|----------|------------|
| [docs/01_SRS.md](docs/01_SRS.md) | Software Requirements Specification (what we're building & why) |
| [docs/02_ARCHITECTURE.md](docs/02_ARCHITECTURE.md) | Architecture diagram & design decisions |

## Dry-run progress
- [x] **Phase 1 — Design & Architecture** (mirrors competition Day 1)
- [ ] **Phase 2 — TDD Development** (mirrors Day 2)
- [ ] **Phase 3 — CI/CD, Deploy & Pitch** (mirrors Day 3)
