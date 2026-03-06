package med.voll.api.domain.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.appointment.validations.ValidatorScheduleAppointment;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exception.ValidationException;

@Service
public class ScheduleAppointments {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorScheduleAppointment> validators;

    public void toSchedule(ScheduleAppointmentData data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient ID does not exist");
        }

        if (data.idDoctor() != null
                && !patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient ID does not exist");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = chooseDoctor(data);

        var appointment = new Appointment(null, doctor, patient, data.date(), null);

        appointmentRepository.save(appointment);

    }

    private Doctor chooseDoctor(ScheduleAppointmentData data) {

        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Speciality is required when the doctor is null.");
        }

        return doctorRepository.chooseRandomDoctorFreeOnTheDate(data.specialty(), data.date());

    }

    public void cancel(CancellationAppointmentData data) {

        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("Appointment with ID passed does not exist!");
        }

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }

}
