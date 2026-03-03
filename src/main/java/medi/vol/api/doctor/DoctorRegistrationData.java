package medi.vol.api.doctor;

import medi.vol.api.adress.Adress;

public record DoctorRegistrationData(String name, String email, String crm, Specialty specialty, Adress adress) {

}
