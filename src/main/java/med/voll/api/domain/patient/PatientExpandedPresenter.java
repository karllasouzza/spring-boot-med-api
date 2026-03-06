package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientExpandedPresenter(
        Long id, String name, String email, String phone, String Cpf, Address Address) {

    public PatientExpandedPresenter(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(),
                patient.getAddress());
    }
}
