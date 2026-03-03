package medi.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import medi.vol.api.doctor.Doctor;
import medi.vol.api.doctor.DoctorRegistrationData;
import medi.vol.api.doctor.DoctorRepository;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody DoctorRegistrationData data) {
        repository.save(new Doctor(data));
    }

}
