package medi.vol.api.adress;

public record AdressData(String street, String neighborhood, String zipCode, String city, String state, String additionalInfo,
        String number) {
}
