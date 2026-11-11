# Selection Sort Trace – Hospital Appointment Desk

Sort by `(urgency, arrival time)`. Lower urgency number means higher priority.

| Appointment | Urgency | Arrival |
|---|---:|---|
| A1 | 3 | 09:00 |
| A2 | 1 | 10:00 |
| A3 | 1 | 08:30 |
| A4 | 2 | 08:45 |

**Pass 1:** The smallest key is A3 `(1, 08:30)`. Swap A1 and A3.

`A3, A2, A1, A4`

**Pass 2:** Search positions 2–4. A2 `(1, 10:00)` is already the smallest in this section. No swap.

`A3, A2, A1, A4`

**Pass 3:** A4 `(2, 08:45)` is smaller than A1 `(3, 09:00)`. Swap A1 and A4.

`A3, A2, A4, A1`

**Final order:** A3, A2, A4, A1.

Selection sort uses O(n²) comparisons and at most n−1 actual swaps.
