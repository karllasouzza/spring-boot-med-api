package medi.vol.api.doctor;

import jakarta.validation.constraints.NotNull;
import medi.vol.api.adress.AdressData;

public record UpdateDoctorData(
        @NotNull Long id,
        String name,
        String phone,
        AdressData adressData) {
}
