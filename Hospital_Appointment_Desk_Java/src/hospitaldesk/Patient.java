package hospitaldesk;

public class Patient {
    private final String patientId;
    private final String name;
    private final int age;
    private final String department;

    public Patient(String patientId, String name, int age, String department) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return patientId + " | " + name + " | age=" + age + " | " + department;
    }
}
