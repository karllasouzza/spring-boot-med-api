package med.voll.api.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.infra.exception.ValidationException;

@Component
public class ValidatorDoctorIsActive implements ValidatorScheduleAppointment {

    @Autowired
    private DoctorRepository repository;

    public void validate(ScheduleAppointmentData data) {
        if (data.idDoctor() == null) {
            return;
        }

        var isDoctorActive = repository.existsByIdAndDeletedAtNull(data.idDoctor());

        if (!isDoctorActive) {
            throw new ValidationException("The appointment cannot be scheduled with the excluded doctor.");
        }
    }

}
