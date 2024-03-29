package com.wengerwatson.patienthub.controller;

import com.wengerwatson.patienthub.config.PatientAPI;
import com.wengerwatson.patienthub.model.Patient;
import com.wengerwatson.patienthub.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Validated
public class PatientController implements PatientAPI {

    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get patient by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@Parameter(description = "Patient ID", example = "1") @PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @Operation(summary = "Create or Update patient by passing mandatory data")
    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        Patient createdPatient = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @Operation(summary = "Update patient by passing mandatory data")
    @PostMapping
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient) {
        Patient createdPatient = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @Operation(summary = "Delete patient by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/allPatients")
    public ResponseEntity<List<Patient>> getAll() {
        var patients = patientService.getAll();
        return ResponseEntity.ok(patients);
    }
}
