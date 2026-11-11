# Set 3 – Hospital Appointment Desk (Java)

## Aim
Build a multi-speciality hospital appointment desk using searching, sorting, hashing, queues, stacks and dynamic programming.

## Requirements
- JDK 8 or later
- No external libraries

## Folder structure
```text
Hospital_Appointment_Desk_Java/
├── src/hospitaldesk/
│   ├── Patient.java
│   ├── Appointment.java
│   ├── PatientSearch.java
│   ├── AppointmentSorting.java
│   ├── BookingManager.java
│   ├── CustomStructures.java
│   ├── SchedulingAlgorithms.java
│   ├── SampleData.java
│   └── Main.java
├── tests/HospitalDeskTests.java
├── docs/SORTING_TRACE.md
└── README.md
```

## Compile and run (Windows)
Open Command Prompt in the project folder:

```bat
javac -d out src\hospitaldesk\*.java
java -cp out hospitaldesk.Main
```

## Run tests
```bat
javac -d out src\hospitaldesk\*.java tests\HospitalDeskTests.java
java -cp out hospitaldesk.HospitalDeskTests
```

## Google Colab
Upload and extract the ZIP in Colab, then run:

```bash
!javac -d out src/hospitaldesk/*.java
!java -cp out hospitaldesk.Main
```

## Task 1 – Linear and binary search
The program generates 20,000 patients with IDs P00001–P20000 and performs 1,000 successful look-ups. Linear search scans records one by one and works on unsorted data: O(n). Binary search repeatedly halves the search range and needs records sorted by patient ID: O(log n). The one-time sort costs O(n log n). Runtime values are measured on the machine running the program and will vary.

## Task 2 – Selection sort and quicksort
The appointment comparator sorts by urgency and then arrival time. Urgency codes are 1=Critical, 2=High and 3=Normal. Selection sort takes O(n²) time and at most n−1 swaps. Quicksort is average O(n log n), worst O(n²). The program prints actual swap counts; its timings vary by system and input.

## Task 3 – Double-booking detection
A duplicate is the same patient assigned to the same time slot, even if the doctor ID differs. A HashSet stores the patient-ID/slot key. Duplicate detection is expected O(n) total time, with O(1) average membership checks. Repeatedly scanning the full booking list for every insertion can take O(n²) total time, so a list scan is not suitable for a large roster.

## Task 4 – Queue and stack from scratch
The walk-in queue is FIFO (first in, first out), so the earliest arriving patient is served first. The recent-patient history is LIFO (last in, first out), so the latest viewed patient is retrieved first. Both are implemented with linked nodes, not Java's built-in Queue or Stack classes. Enqueue/dequeue and push/pop are O(1).

## Task 5 – Dynamic programming and greedy comparison
Each appointment is an interval (start, end) in minutes from the start of a shift. The objective is to maximize the number of non-overlapping appointments, with every appointment worth 1. The DP recurrence is:

`DP[i] = max(DP[i-1], 1 + DP[p(i)])`

where p(i) is the number of earlier appointments that finish no later than the current appointment starts. Sorting plus binary search gives O(n log n) time. The greedy baseline selects the compatible appointment with the earliest finish time. For this unweighted maximum-count interval problem, earliest-finish-time greedy is also optimal; DP is implemented because the assignment requests dynamic programming. If appointments had different profits or priorities as their values, weighted interval scheduling would be required.

## Complexity summary

| Task | Algorithm | Time |
|---|---|---|
| Patient lookup | Linear search | O(n) |
| Patient lookup | Binary search | O(log n) |
| Appointment ordering | Selection sort | O(n²) |
| Appointment ordering | Quicksort | Average O(n log n), worst O(n²) |
| Duplicate detection | HashSet | Expected O(n) total |
| Walk-in line | Linked queue | O(1) enqueue/dequeue |
| Viewed history | Linked stack | O(1) push/pop |
| Shift scheduling | DP | O(n log n) |

## Deliverables
Java source code, generated test data, executable demonstration, test class, sorting trace, complexity notes and data-structure explanation.
