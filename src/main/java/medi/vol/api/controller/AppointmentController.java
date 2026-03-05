package medi.vol.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.vol.api.domain.appointment.AppointmentPresenter;
import medi.vol.api.domain.appointment.CancellationAppointmentData;
import medi.vol.api.domain.appointment.ScheduleAppointmentData;
import medi.vol.api.domain.appointment.ScheduleAppointments;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private ScheduleAppointments schedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleAppointmentData data) {
        schedule.toSchedule(data);

        return ResponseEntity.ok(new AppointmentPresenter(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid CancellationAppointmentData data) {
        schedule.cancel(data);

        return ResponseEntity.noContent().build();
    }

}
