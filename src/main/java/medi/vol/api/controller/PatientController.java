package medi.vol.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import medi.vol.api.patient.PatientRegistratioData;

@RestController
@RequestMapping("patients")
public class PatientController {

    @PostMapping
    public void registration(@RequestBody PatientRegistratioData data){
        System.out.println(data);
    } 

}
