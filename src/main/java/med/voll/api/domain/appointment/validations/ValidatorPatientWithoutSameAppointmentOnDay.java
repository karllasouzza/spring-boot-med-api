package med.voll.api.domain.appointment.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.infra.exception.ValidationException;

@Component
public class ValidatorPatientWithoutSameAppointmentOnDay implements ValidatorScheduleAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentData data) {
        var firstTime = data.date().withHour(7);
        var lastTime = data.date().withHour(18);
        var patientHasAnotherAppointmentOnSameDay = repository.existsByPatientIdAndDateBetween(data.idPatient(),
                firstTime, lastTime);

        if (patientHasAnotherAppointmentOnSameDay) {
            throw new ValidationException("Patient has another appointment on same day");
        }
    }
}
