package medi.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import medi.vol.api.patient.Patient;
import medi.vol.api.patient.PatientListData;
import medi.vol.api.patient.PatientRegistratioData;
import medi.vol.api.patient.PatientRepository;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void registration(@RequestBody PatientRegistratioData data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListData> list(@PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
        return repository.findAll(pagination).map(PatientListData::new);
    }

}
