package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorRegistrationData> doctorRegistrationDataJacksonTester;

    @Autowired
    private JacksonTester<DoctorDataPresenter> doctorDataPresenterJacksonTester;

    @MockitoBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Should be bad request (400) when the infos is invalid")
    @WithMockUser
    void registerShouldBeBadRequestWhenInfosIsInvalid() throws Exception {
        var response = mvc
                .perform(post("/doctors")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Should return created (201) when the infos is valid")
    @WithMockUser
    void registerShouldReturnCreatedWhenInfosIsValid() throws Exception {
        var dataRegistration = new DoctorRegistrationData(
                "Doctor",
                "doctor@doctor.med",
                "17888888888",
                "123456",
                Specialty.DERMATOLOGY,
                addressData()
        );

        when(repository.save(any())).thenReturn(new Doctor(dataRegistration));

        var response = mvc
                .perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorRegistrationDataJacksonTester.write(dataRegistration).getJson())
                ).andReturn().getResponse();

        var dataPresenter = new DoctorDataPresenter(
                null,
                dataRegistration.name(),
                dataRegistration.email(),
                dataRegistration.crm(),
                dataRegistration.phone(),
                dataRegistration.specialty(),
                new Address(dataRegistration.address())
        );

        var expectedJson = doctorDataPresenterJacksonTester.write(dataPresenter).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }


    private AddressData addressData() {
        return new AddressData(
                "street xpo",
                "neighborhood",
                "00000000",
                "Basilia",
                "DF",
                null,
                null
        );
    }
}