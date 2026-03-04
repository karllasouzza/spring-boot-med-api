package medi.vol.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import medi.vol.api.domain.adress.AdressData;

public record UpdatePatientData(
        @NotNull Long id,
        String name,
        String phone,
        AdressData adressData) {

}
