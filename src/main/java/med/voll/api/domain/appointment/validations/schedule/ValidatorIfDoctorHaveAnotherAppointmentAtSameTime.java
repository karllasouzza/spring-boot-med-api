package med.voll.api.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.infra.exception.ValidationException;

@Component
public class ValidatorIfDoctorHaveAnotherAppointmentAtSameTime implements ValidatorScheduleAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var doctorHasAnotherAppointmentAtSameTime = repository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
        if (doctorHasAnotherAppointmentAtSameTime) {
            throw new ValidationException("Doctor has another appointment at same time.");
        }
    }

}
