package hospitaldesk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HospitalDeskTests {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        List<Patient> patients = SampleData.makePatients(5);
        check(PatientSearch.linearSearch(patients, "P00003") != null, "linear search");
        check(PatientSearch.binarySearch(patients, "P00003") != null, "binary search");

        List<Appointment> appointments = Arrays.asList(
                new Appointment("A1", "P1", "D1", 3, "09:00"),
                new Appointment("A2", "P2", "D1", 1, "10:00"),
                new Appointment("A3", "P3", "D1", 1, "08:30"));
        AppointmentSorting.SortResult s1 = AppointmentSorting.selectionSort(appointments);
        AppointmentSorting.SortResult s2 = AppointmentSorting.quickSort(appointments);
        check(s1.appointments.get(0).getAppointmentId().equals("A3"), "selection sort");
        check(s2.appointments.get(0).getAppointmentId().equals("A3"), "quick sort");

        List<BookingManager.Booking> bookings = new ArrayList<>();
        check(BookingManager.bookSlot(bookings, "P1", "9AM", "D1"), "first booking");
        check(!BookingManager.bookSlot(bookings, "P1", "9AM", "D2"), "duplicate blocked");
        check(BookingManager.findDoubleBookings(Arrays.asList(
                bookings.get(0), bookings.get(0))).size() == 1, "duplicate detection");

        CustomStructures.WalkInQueue<String> queue = new CustomStructures.WalkInQueue<>();
        queue.enqueue("A"); queue.enqueue("B");
        check(queue.dequeue().equals("A"), "FIFO queue");
        CustomStructures.RecentlyViewedStack<String> stack =
                new CustomStructures.RecentlyViewedStack<>();
        stack.push("A"); stack.push("B");
        check(stack.pop().equals("B"), "LIFO stack");

        List<SchedulingAlgorithms.TimeSlot> slots = Arrays.asList(
                new SchedulingAlgorithms.TimeSlot(0, 30),
                new SchedulingAlgorithms.TimeSlot(10, 20),
                new SchedulingAlgorithms.TimeSlot(20, 45),
                new SchedulingAlgorithms.TimeSlot(30, 50),
                new SchedulingAlgorithms.TimeSlot(50, 80));
        check(SchedulingAlgorithms.maximumAppointmentsDP(slots).count() == 3, "DP schedule");
        check(SchedulingAlgorithms.maximumAppointmentsGreedy(slots).count() == 3, "greedy schedule");

        System.out.println("All 5 Java test groups passed.");
    }
}
