package medi.vol.api.domain.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import medi.vol.api.domain.doctor.Doctor;
import medi.vol.api.domain.doctor.DoctorRepository;
import medi.vol.api.domain.patient.PatientRepository;

@Service
public class ScheduleAppointments {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void toSchedule(ScheduleAppointmentData scheduleAppointmentData) {

        if (!patientRepository.existsById(scheduleAppointmentData.idPatient())) {
            throw new ValidationException("Patient ID does not exist");
        }

        if (scheduleAppointmentData.idDoctor() != null
                && !patientRepository.existsById(scheduleAppointmentData.idPatient())) {
            throw new ValidationException("Patient ID does not exist");
        }

        var patient = patientRepository.getReferenceById(scheduleAppointmentData.idPatient());
        var doctor = chooseDoctor(scheduleAppointmentData);

        var appointment = new Appointment(null, doctor, patient, scheduleAppointmentData.data(), null);

        appointmentRepository.save(appointment);

    }

    private Doctor chooseDoctor(ScheduleAppointmentData scheduleAppointmentData) {
        var idDoctor = scheduleAppointmentData.idDoctor();
        var speciality = scheduleAppointmentData.specialty();
        var date = scheduleAppointmentData.data();

        if (idDoctor != null) {
            return doctorRepository.getReferenceById(idDoctor);
        }

        if (speciality == null) {
            throw new ValidationException("Speciality is required when the doctor is null.");
        }

        return doctorRepository.chooseRandomDoctorFreeOnTheDate(speciality, date);

    }
    
    public void cancel(CancellationAppointmentData data){

        if(!appointmentRepository.existsById(data.idAppointment())){
            throw new ValidationException("Appointment with ID passed does not exist!");
        }

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }

}
