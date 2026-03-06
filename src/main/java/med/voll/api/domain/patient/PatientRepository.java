package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByDeletedAtNull(Pageable pagination);

        @Query(value = """
            SELECT CASE
                WHEN p.deleted_at IS NOT NULL THEN TRUE
                ELSE FALSE
            END
            FROM patients p
                WHERE p.id = :id
            """, nativeQuery = true)
    Boolean findDeletedAtNullById(@Param("id") Long id);

}
