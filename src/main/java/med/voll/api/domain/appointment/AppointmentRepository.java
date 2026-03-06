package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);

    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);

    Boolean existsByDoctorIdAndDateAndReasonCancellationIsNull(Long doctorId, LocalDateTime date);
}
