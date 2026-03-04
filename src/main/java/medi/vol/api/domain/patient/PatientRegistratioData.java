package medi.vol.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medi.vol.api.domain.adress.Adress;

public record PatientRegistratioData(

        @NotBlank String name,

        @NotBlank @Email String email,

        @NotBlank String Phone,

        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String cpf,

        @NotNull @Valid Adress adress) {

}
