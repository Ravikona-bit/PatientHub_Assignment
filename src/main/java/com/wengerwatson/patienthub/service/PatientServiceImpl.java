package com.wengerwatson.patienthub.service;

import com.wengerwatson.patienthub.exception.PatientNotFoundException;
import com.wengerwatson.patienthub.model.Patient;
import com.wengerwatson.patienthub.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Cacheable("patients")
    public Patient findById(Long id) {
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            throw new PatientNotFoundException("Patient not found with ID: " + id);
        }
        return patient;
    }

    @Override
    @Cacheable("patients")
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @CacheEvict(value = "patients", key = "#id")
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Cacheable("patients")
    public List<Patient> getAll() {
        List<Patient> listPatients = patientRepository.findAll();
        if (null==listPatients && listPatients.isEmpty()) {
            throw new PatientNotFoundException("No Patient Found in the Data Source");
        }
        return listPatients;
    }
}
