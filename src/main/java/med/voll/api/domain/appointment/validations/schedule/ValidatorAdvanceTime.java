package med.voll.api.domain.appointment.validations.schedule;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.ScheduleAppointmentData;

@Component("ValidatorAdvanceTimeSchedule")
public class ValidatorAdvanceTime implements ValidatorScheduleAppointment {

    public void validate(ScheduleAppointmentData data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var diffInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (diffInMinutes < 30) {
            throw new med.voll.api.infra.exception.ValidationException("Appointment must be scheduled at least 30 minutes in advance");
        }
    }

}
