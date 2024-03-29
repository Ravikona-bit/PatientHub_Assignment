package com.wengerwatson.patienthub.repository;

import com.wengerwatson.patienthub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findById(Long id);

    void deleteById(Long id);
}
