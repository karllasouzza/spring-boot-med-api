package medi.vol.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancellationAppointmentData(
    @NotNull
    Long idAppointment,

    @NotNull
    ReasonCancellation reason
    
) {

}
