package med.voll.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByDeletedAtNull(Pageable pagination);

    boolean existsByIdAndDeletedAtNull(Long id);

    @Query(value = """
            SELECT d.* FROM doctors d
            WHERE d.deleted_at IS NULL
                AND d.specialty = :speciality
                AND d.id NOT IN(
                    SELECT a.doctor_id FROM appointments a
                    WHERE a.data = :date
                    AND a.reason_cancellation IS NULL
                )
            ORDER BY RAND()
            LIMIT 1
            """, nativeQuery = true)
    Doctor chooseRandomDoctorFreeOnTheDate(@Param("speciality") Specialty speciality,
            @Param("date") LocalDateTime date);
}
