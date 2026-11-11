package hospitaldesk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingManager {
    public static class Booking {
        private final String patientId;
        private final String slot;
        private final String doctorId;

        public Booking(String patientId, String slot, String doctorId) {
            this.patientId = patientId;
            this.slot = slot;
            this.doctorId = doctorId;
        }

        public String getPatientId() { return patientId; }
        public String getSlot() { return slot; }
        public String getDoctorId() { return doctorId; }

        @Override
        public String toString() {
            return patientId + " | " + slot + " | doctor=" + doctorId;
        }
    }

    private static String key(Booking booking) {
        return booking.getPatientId() + "|" + booking.getSlot();
    }

    // HashSet provides expected O(1) membership checks.
    public static List<Booking> findDoubleBookings(List<Booking> bookings) {
        Set<String> seen = new HashSet<>();
        List<Booking> duplicates = new ArrayList<>();

        for (Booking booking : bookings) {
            if (!seen.add(key(booking))) {
                duplicates.add(booking);
            }
        }
        return duplicates;
    }

    public static boolean bookSlot(List<Booking> bookings, String patientId,
                                   String slot, String doctorId) {
        String wantedKey = patientId + "|" + slot;
        for (Booking booking : bookings) {
            if (key(booking).equals(wantedKey)) {
                return false;
            }
        }
        bookings.add(new Booking(patientId, slot, doctorId));
        return true;
    }
}
