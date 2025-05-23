package com.isa.PatientRegistration.controller;

import com.isa.PatientRegistration.model.Patient;
import com.isa.PatientRegistration.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Patient> registerPatient(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String gender,
            @RequestParam String address,
            @RequestParam String dateOfBirth,
            @RequestParam MultipartFile photo
    ) throws IOException {


        Patient patient = new Patient();
        patient.setName(name);
        patient.setEmail(email);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
        patient.setPhoto(photo.getBytes());
        patient.setPhotoType(photo.getContentType());

        Patient savedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(savedPatient);
    }

    //************************************************

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//*****************************************************



    @GetMapping("/patients")
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

//******************************************************************


    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //************************************************************

    @PutMapping(value = "/patients/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String gender,
            @RequestParam String address,
            @RequestParam String dateOfBirth,
            @RequestParam(required = false) MultipartFile photo
    ) throws IOException {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(name);
        patient.setEmail(email);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
        if (photo != null && !photo.isEmpty()) {
            patient.setPhoto(photo.getBytes());
            patient.setPhotoType(photo.getContentType());
        }

        Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }

}

