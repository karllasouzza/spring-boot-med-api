package medi.vol.api.domain.patient;

import medi.vol.api.domain.adress.Adress;

public record PatientExpandedPresenter(
        Long id, String name, String email, String Phone, String Cpf, Adress adress) {

    public PatientExpandedPresenter(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(),
                patient.getAdress());
    }
}
