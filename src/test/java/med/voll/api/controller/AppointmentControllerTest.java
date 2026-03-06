package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentPresenter;
import med.voll.api.domain.appointment.ScheduleAppointmentData;
import med.voll.api.domain.appointment.ScheduleAppointments;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentData> scheduleAppointmentDataJacksonTester;

    @Autowired
    private JacksonTester<AppointmentPresenter> appointmentPresenterJacksonTester;

    @MockitoBean
    private ScheduleAppointments scheduleAppointments;

    @Test
    @DisplayName("Should return bad request (400) when the infos is invalids")
    @WithMockUser
    void testScheduleWithInvalidInfos() throws Exception {
        var response = mvc.perform(post("/appointments")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return ok (200) when the infos is valid")
    @WithMockUser
    void testScheduleWithValidInfos() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var appointment = new AppointmentPresenter(null, 2L, 5L, date);

        when(scheduleAppointments.toSchedule(any())).thenReturn(appointment);

        var response = mvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON)
                .content(scheduleAppointmentDataJacksonTester.write(
                        new ScheduleAppointmentData(2L, 5L, date, specialty)).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentPresenterJacksonTester.write(appointment).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);

    }
}
