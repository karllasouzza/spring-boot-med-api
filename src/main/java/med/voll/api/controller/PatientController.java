package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientExpandedPresenter;
import med.voll.api.domain.patient.PatientListData;
import med.voll.api.domain.patient.PatientRegistratioData;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.UpdatePatientData;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registration(@RequestBody @Valid PatientRegistratioData data,
            UriComponentsBuilder uriBuilder) {
        Patient newPatient = new Patient(data);
        repository.save(newPatient);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(newPatient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientExpandedPresenter(newPatient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> list(
            @PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
        var page = repository.findAllByDeletedAtNull(pagination).map(PatientListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Patient currentPatient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientExpandedPresenter(currentPatient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePatientData data) {
        Patient currentPatient = repository.getReferenceById(data.id());
        currentPatient.updatePatientData(data);

        return ResponseEntity.ok(new PatientExpandedPresenter(currentPatient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Patient currentPatient = repository.getReferenceById(id);
        currentPatient.softDelete();

        return ResponseEntity.noContent().build();
    }
}
