package medi.vol.api.adress;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
        private String street;
        private String neighborhood;
        private String zip_code;
        private String city;
        private String state;
        private String additional_info;
        private String number;

        public Adress(Adress data) {
                this.street = data.street;
                this.neighborhood = data.neighborhood;
                this.zip_code = data.zip_code;
                this.city = data.city;
                this.state = data.state;
                this.additional_info = data.additional_info;
                this.number = data.number;
        }

        public void updateAdressData(AdressData data) {
                if (data.street() != null) {
                        this.street = data.street();
                }

                if (data.neighborhood() != null) {
                        this.neighborhood = data.neighborhood();
                }

                if (data.zip_code() != null) {
                        this.zip_code = data.zip_code();
                }

                if (data.city() != null) {
                        this.city = data.city();
                }

                if (data.state() != null) {
                        this.state = data.state();
                }

                if (data.additional_info() != null) {
                        this.additional_info = data.additional_info();
                }

                if (data.number() != null) {
                        this.number = data.number();
                }
        }
}
