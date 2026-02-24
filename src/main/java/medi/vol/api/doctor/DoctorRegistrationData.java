package medi.vol.api.doctor;

import medi.vol.api.adress.AdressData;

public record DoctorRegistrationData(String name, String email, String crm, Specialty specialty, AdressData adress) {

}
