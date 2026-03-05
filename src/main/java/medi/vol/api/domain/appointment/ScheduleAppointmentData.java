package medi.vol.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ScheduleAppointmentData(
                Long idDoctor,

                @NotNull Long idPatient,

                @NotNull @Future LocalDateTime data

) {

}
