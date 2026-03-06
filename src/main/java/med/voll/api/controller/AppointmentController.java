package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.CancellationAppointmentData;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.appointment.ScheduleAppointments;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private ScheduleAppointments schedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleAppointmentData data) {
        var dto = schedule.toSchedule(data);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid CancellationAppointmentData data) {
        schedule.cancel(data);

        return ResponseEntity.noContent().build();
    }

}
