package medi.vol.api.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdressData(

                @NotBlank String street,
                @NotBlank String neighborhood,
                @NotBlank @Pattern(regexp = "\\d{8}") String zipCode,
                @NotBlank String city,
                @NotBlank String state,
                String additionalInfo,
                String number) {
}
