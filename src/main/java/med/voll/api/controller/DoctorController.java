package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorDataPresenter;
import med.voll.api.domain.doctor.DoctorListData;
import med.voll.api.domain.doctor.DoctorRegistrationData;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.UpdateDoctorData;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegistrationData data, UriComponentsBuilder uriBuilder) {
        var newDoctor = new Doctor(data);

        repository.save(newDoctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(newDoctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDataPresenter(newDoctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> list(
            @PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
        var page = repository.findAllByDeletedAtNull(pagination).map(DoctorListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Doctor currentDoctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDataPresenter(currentDoctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDoctorData data) {
        Doctor currentDoctor = repository.getReferenceById(data.id());
        currentDoctor.updateDoctorData(data);

        return ResponseEntity.ok(new DoctorDataPresenter(currentDoctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Doctor currentDoctor = repository.getReferenceById(id);
        currentDoctor.softDelete();

        return ResponseEntity.noContent().build();
    }

}
