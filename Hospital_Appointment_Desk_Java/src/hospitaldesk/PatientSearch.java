package hospitaldesk;

import java.util.List;

public class PatientSearch {
    // Linear search works whether or not the list is sorted.
    public static Patient linearSearch(List<Patient> patients, String patientId) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    // Binary search requires patient records sorted by patient ID.
    public static Patient binarySearch(List<Patient> sortedPatients, String patientId) {
        int low = 0;
        int high = sortedPatients.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedPatients.get(mid).getPatientId().compareTo(patientId);

            if (comparison == 0) return sortedPatients.get(mid);
            if (comparison < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}
