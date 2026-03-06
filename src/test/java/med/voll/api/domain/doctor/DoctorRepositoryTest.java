package med.voll.api.domain.doctor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegistrationData;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should be null when doctor is not available on the date")
    void testChooseRandomDoctorFreeOnTheDateWhenDoctorIsNotAvailableOnDate() {
        var mondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("doctor", "doctor@vol.med", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("patient", "patient@patient.com", "12345678844");
        registerAppointment(doctor, patient, mondayAt10);

        var availableDoctor = doctorRepository.chooseRandomDoctorFreeOnTheDate(Specialty.CARDIOLOGY, mondayAt10);
        assertThat(availableDoctor).isNull();

    }

    @Test
    @DisplayName("Should return Doctor when he is available on the date")
    void testChooseRandomDoctorFreeOnTheDateWhenDoctorIsAvailableOnDate() {
        var mondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("doctor", "doctor@vol.med", "123456", Specialty.CARDIOLOGY);

        var availableDoctor = doctorRepository.chooseRandomDoctorFreeOnTheDate(Specialty.CARDIOLOGY, mondayAt10);
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorRegistrationData(name, email, crm, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientRegistrationData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorRegistrationData doctorRegistrationData(String name, String email, String crm, Specialty specialty) {
        return new DoctorRegistrationData(name, email, email, crm, specialty, addressData());
    }

    private PatientRegistrationData patientRegistrationData(String name, String email, String cpf) {
        return new PatientRegistrationData(name, email, "1688888888", cpf, addressData());
    }

    private AddressData addressData() {
        return new AddressData("street xpo", "neighborhood", "00000000", "Brasilia", "DF", null, null);
    }
}
