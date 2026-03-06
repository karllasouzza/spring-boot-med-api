package med.voll.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByDeletedAtNull(Pageable pagination);

    @Query(value = """
            SELECT CASE
                WHEN d.deleted_at IS NOT NULL THEN TRUE
                ELSE FALSE
            END
            FROM doctors d
                WHERE d.id = :id
            """, nativeQuery = true)
    Boolean findDeletedAtNullById(@Param("id") Long id);

    @Query(value = """
            SELECT d.* FROM doctors d
            WHERE d.deleted_at IS NULL
                AND d.specialty = :speciality
                AND d.id NOT IN(
                    SELECT a.doctor_id FROM appointments a
                    WHERE a.data = :date
                )
            ORDER BY RAND()
            LIMIT 1
            """, nativeQuery = true)
    Doctor chooseRandomDoctorFreeOnTheDate(@Param("speciality") Specialty speciality,
            @Param("date") LocalDateTime date);
}
