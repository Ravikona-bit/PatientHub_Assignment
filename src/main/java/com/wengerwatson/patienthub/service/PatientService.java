package com.wengerwatson.patienthub.service;

import com.wengerwatson.patienthub.model.Patient;

import java.util.List;

public interface PatientService {
    Patient findById(Long id);
    Patient save(Patient patient);
    void deleteById(Long id);

    List<Patient> getAll();
}
