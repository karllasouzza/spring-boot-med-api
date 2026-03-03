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
        private String zipCode;
        private String city;
        private String state;
        private String additionalInfo;
        private String number;

        public Adress(Adress data) {
                this.street = data.street;
                this.neighborhood = data.neighborhood;
                this.zipCode = data.zipCode;
                this.city = data.city;
                this.state = data.state;
                this.additionalInfo = data.additionalInfo;
                this.number = data.number;
        }
}
