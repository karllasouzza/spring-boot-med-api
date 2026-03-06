package med.voll.api.domain.appointment.validations.cancellation;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.CancellationAppointmentData;
import med.voll.api.infra.exception.ValidationException;

@Component("ValidatorAdvanceTimeCancellation")
public class ValidatorAdvanceTime implements ValidatorAppointmentCancellation {

    private AppointmentRepository repository;

    @Override
    public void validate(CancellationAppointmentData data) {
        var appointment = repository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var diffInHours = Duration.between(now, appointment.getDate()).toHours();

        if (diffInHours < 24) {
            throw new ValidationException("Appointments can oly be cancelled with a minimum of 24 hours");
        }
    }

}
