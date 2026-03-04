package medi.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.vol.api.patient.Patient;
import medi.vol.api.patient.PatientListData;
import medi.vol.api.patient.PatientRegistratioData;
import medi.vol.api.patient.PatientRepository;
import medi.vol.api.patient.UpdatePatientData;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public Patient registration(@RequestBody @Valid PatientRegistratioData data) {
        Patient newPatient = repository.save(new Patient(data));
        return newPatient;
    }

    @GetMapping
    public Page<PatientListData> list(@PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
        return repository.findAllByDeletedAtNull(pagination).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientData data) {
        Patient currentPatient = repository.getReferenceById(data.id());
        currentPatient.updatePatientData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id) {
        Patient currentPatient = repository.getReferenceById(id);
        currentPatient.softDelete();
    }
}
