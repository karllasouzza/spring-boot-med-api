package med.voll.api.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
        private String street;
        private String neighborhood;

        @Column(name = "zip_code")
        private String zipCode;

        private String city;
        private String state;

        @Column(name = "additional_info")
        
        private String additionalInfo;
        private String number;

        public Address(Address data) {
                this.street = data.street;
                this.neighborhood = data.neighborhood;

                this.zipCode = data.zipCode;
                this.city = data.city;
                this.state = data.state;
                this.additionalInfo = data.additionalInfo;
                this.number = data.number;
        }

        public void updateAddressData(AddressData data) {
                if (data.street() != null) {
                        this.street = data.street();
                }

                if (data.neighborhood() != null) {
                        this.neighborhood = data.neighborhood();
                }

                if (data.zipCode() != null) {
                        this.zipCode = data.zipCode();
                }

                if (data.city() != null) {
                        this.city = data.city();
                }

                if (data.state() != null) {
                        this.state = data.state();
                }

                if (data.additionalInfo() != null) {
                        this.additionalInfo = data.additionalInfo();
                }

                if (data.number() != null) {
                        this.number = data.number();
                }
        }
}
