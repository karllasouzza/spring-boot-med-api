package medi.vol.api.patient;

import jakarta.validation.constraints.NotNull;
import medi.vol.api.adress.AdressData;

public record UpdatePatientData(
        @NotNull Long id,
        String name,
        String phone,
        AdressData adressData) {

}
