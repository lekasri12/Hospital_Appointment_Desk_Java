package hospitaldesk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleData {
    public static List<Patient> makePatients(int count) {
        String[] departments = {"Cardiology", "Neurology", "Orthopedics",
                "Pediatrics", "Dermatology"};
        Random random = new Random(42);
        List<Patient> patients = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String id = String.format("P%05d", i);
            patients.add(new Patient(id, "Patient " + String.format("%05d", i),
                    1 + random.nextInt(95), departments[random.nextInt(departments.length)]));
        }
        return patients;
    }

    public static List<Appointment> makeAppointments(int count) {
        Random random = new Random(7);
        List<Appointment> appointments = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String id = String.format("A%03d", i);
            String patientId = String.format("P%05d", i);
            String doctorId = String.format("D%02d", 1 + random.nextInt(5));
            int urgency = 1 + random.nextInt(3);
            int hour = 8 + random.nextInt(9);
            int minute = random.nextInt(4) * 15;
            appointments.add(new Appointment(id, patientId, doctorId, urgency,
                    String.format("%02d:%02d", hour, minute)));
        }
        return appointments;
    }
}
