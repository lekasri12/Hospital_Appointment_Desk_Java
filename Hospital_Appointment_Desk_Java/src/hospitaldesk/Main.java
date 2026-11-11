package hospitaldesk;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("             HOSPITAL APPOINTMENT DESK - JAVA");
        System.out.println("============================================================");

        // TASK 1: Search 20,000 patients with 1,000 look-ups.
        List<Patient> patients = SampleData.makePatients(20_000);
        List<String> targets = new ArrayList<>();
        for (int i = 1; i <= 1_000; i++) targets.add(String.format("P%05d", i));

        long start = System.nanoTime();
        for (String id : targets) PatientSearch.linearSearch(patients, id);
        long linearNanos = System.nanoTime() - start;

        List<Patient> sortedPatients = new ArrayList<>(patients);
        sortedPatients.sort((a, b) -> a.getPatientId().compareTo(b.getPatientId()));
        start = System.nanoTime();
        for (String id : targets) PatientSearch.binarySearch(sortedPatients, id);
        long binaryNanos = System.nanoTime() - start;

        System.out.println("\nTASK 1 - PATIENT SEARCH");
        System.out.printf("Linear search : %.3f ms total, %.6f ms average%n",
                linearNanos / 1_000_000.0, linearNanos / 1_000_000.0 / targets.size());
        System.out.printf("Binary search : %.3f ms total, %.6f ms average%n",
                binaryNanos / 1_000_000.0, binaryNanos / 1_000_000.0 / targets.size());
        System.out.println("Example patient: " +
                PatientSearch.binarySearch(sortedPatients, "P00042"));

        // TASK 2: Sort by urgency first, then arrival time.
        List<Appointment> appointments = SampleData.makeAppointments(12);
        AppointmentSorting.SortResult selection = AppointmentSorting.selectionSort(appointments);
        AppointmentSorting.SortResult quick = AppointmentSorting.quickSort(appointments);
        System.out.println("\nTASK 2 - APPOINTMENT SORTING");
        System.out.printf("Selection sort: swaps=%d, sorted=%s%n",
                selection.swaps, selection.appointments);
        System.out.printf("Quick sort    : swaps=%d, sorted=%s%n",
                quick.swaps, quick.appointments);

        // TASK 3: Detect duplicate patient + slot combinations.
        List<BookingManager.Booking> bookings = new ArrayList<>();
        bookings.add(new BookingManager.Booking("P00001", "09:00-09:20", "D01"));
        bookings.add(new BookingManager.Booking("P00002", "09:00-09:20", "D02"));
        bookings.add(new BookingManager.Booking("P00001", "09:00-09:20", "D03"));
        System.out.println("\nTASK 3 - DOUBLE BOOKING DETECTION");
        System.out.println("Duplicates: " + BookingManager.findDoubleBookings(bookings));
        System.out.println("New duplicate booking accepted? " +
                BookingManager.bookSlot(bookings, "P00001", "09:00-09:20", "D04"));

        // TASK 4: Queue for walk-ins and stack for viewed-patient history.
        CustomStructures.WalkInQueue<String> walkIns = new CustomStructures.WalkInQueue<>();
        walkIns.enqueue("P00011");
        walkIns.enqueue("P00012");
        walkIns.enqueue("P00013");
        CustomStructures.RecentlyViewedStack<String> history =
                new CustomStructures.RecentlyViewedStack<>();
        history.push("P00021");
        history.push("P00022");
        history.push("P00023");
        System.out.println("\nTASK 4 - QUEUE AND STACK");
        System.out.println("Walk-in patients served: " + walkIns.dequeue()
                + ", " + walkIns.dequeue());
        System.out.println("Most recently viewed: " + history.pop());

        // TASK 5: Maximum number of non-overlapping appointments.
        List<SchedulingAlgorithms.TimeSlot> slots = new ArrayList<>();
        int[][] values = {{0,30},{10,20},{20,45},{30,50},{45,60},
                          {50,80},{60,75},{75,100}};
        for (int[] value : values) {
            slots.add(new SchedulingAlgorithms.TimeSlot(value[0], value[1]));
        }
        SchedulingAlgorithms.ScheduleResult dp =
                SchedulingAlgorithms.maximumAppointmentsDP(slots);
        SchedulingAlgorithms.ScheduleResult greedy =
                SchedulingAlgorithms.maximumAppointmentsGreedy(slots);
        System.out.println("\nTASK 5 - SHIFT SCHEDULING");
        System.out.println("Dynamic programming: " + dp);
        System.out.println("Greedy comparison : " + greedy);
        System.out.println("\nAll five tasks completed.");
    }
}
