package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.CancellationAppointmentData;

public interface ValidatorAppointmentCancellation {

    void validate(CancellationAppointmentData data);

}
