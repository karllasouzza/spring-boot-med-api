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
}
