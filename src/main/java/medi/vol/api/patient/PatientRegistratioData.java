package medi.vol.api.patient;

import medi.vol.api.adress.AdressData;

public record PatientRegistratioData(String name, String email, String Phone, String cpf, AdressData adress) {

}
