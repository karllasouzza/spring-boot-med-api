package medi.vol.api.doctor;

import medi.vol.api.adress.Adress;

public record DoctorExpandedDataPresenter(
        Long id, String name, String email, String crm, String phone, Specialty specialty, Adress Adress) {

    public DoctorExpandedDataPresenter(Doctor doctor) {
                this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAdress());
            }

}
