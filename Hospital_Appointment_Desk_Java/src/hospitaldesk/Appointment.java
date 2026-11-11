package hospitaldesk;

public class Appointment {
    private final String appointmentId;
    private final String patientId;
    private final String doctorId;
    private final int urgency; // 1 = Critical, 2 = High, 3 = Normal
    private final String arrivalTime; // HH:mm, 24-hour format

    public Appointment(String appointmentId, String patientId, String doctorId,
                       int urgency, String arrivalTime) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.urgency = urgency;
        this.arrivalTime = arrivalTime;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public int getUrgency() { return urgency; }
    public String getArrivalTime() { return arrivalTime; }

    @Override
    public String toString() {
        return appointmentId + " | " + patientId + " | doctor=" + doctorId
                + " | urgency=" + urgency + " | arrival=" + arrivalTime;
    }
}
