package medi.vol.api.patient;

import medi.vol.api.adress.Adress;

public record PatientRegistratioData(String name, String email, String Phone, String cpf, Adress adress) {

}
