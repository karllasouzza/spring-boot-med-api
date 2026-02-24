package medi.vol.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import medi.vol.api.doctor.DoctorRegistrationData;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @PostMapping
    public void cadastrar(@RequestBody DoctorRegistrationData data) {

        System.out.println(data);
    }

}
