package med.voll.api.domain.appointment.validations.schedule;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.infra.exception.ValidationException;

@Component
public class ClinicHoursValidator implements ValidatorScheduleAppointment {

    public void validate(ScheduleAppointmentData data) {

        var appointmentData = data.date();

        var sunday = appointmentData.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpened = appointmentData.getHour() < 7;
        var afterClinicClosed = appointmentData.getHour() > 18;

        if (sunday || beforeClinicOpened || afterClinicClosed) {
            throw new ValidationException("Appointment outside of clinic operating hours");
        }

    }

}
