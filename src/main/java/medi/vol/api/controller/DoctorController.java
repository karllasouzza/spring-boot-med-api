package medi.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import medi.vol.api.doctor.Doctor;
import medi.vol.api.doctor.DoctorListData;
import medi.vol.api.doctor.DoctorRegistrationData;
import medi.vol.api.doctor.DoctorRepository;
import medi.vol.api.doctor.UpdateDoctorData;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public Doctor cadastrar(@RequestBody @Valid DoctorRegistrationData data) {
        Doctor doctor = repository.save(new Doctor(data));
        return doctor;
    }

    @GetMapping
    public Page<DoctorListData> list(@PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
        return repository.findAll(pagination).map(DoctorListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctorData data) {
        Doctor currentDoctor = repository.getReferenceById(data.id());
        currentDoctor.updateDoctorData(data);
    }

}
