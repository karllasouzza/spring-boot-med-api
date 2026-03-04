package medi.vol.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import medi.vol.api.domain.adress.AdressData;

public record UpdateDoctorData(
        @NotNull Long id,
        String name,
        String phone,
        AdressData adressData) {
}
