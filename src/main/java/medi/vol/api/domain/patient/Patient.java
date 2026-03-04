package medi.vol.api.domain.patient;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medi.vol.api.domain.adress.Adress;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String phone;

    @Embedded
    private Adress adress;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Patient(PatientRegistratioData data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.phone = data.Phone();
        this.adress = new Adress(data.adress());
    }

    public void updatePatientData(UpdatePatientData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.adressData() != null) {
            this.adress.updateAdressData(data.adressData());
        }
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
