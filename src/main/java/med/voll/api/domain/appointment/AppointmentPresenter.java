package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentPresenter(
        Long id, Long idDoctor, Long idPatient, LocalDateTime data) {

}
