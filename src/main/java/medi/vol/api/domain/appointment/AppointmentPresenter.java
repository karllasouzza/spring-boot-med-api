package medi.vol.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentPresenter(
        Long id, Long idDoctor, Long idPatient, LocalDateTime data) {

}
