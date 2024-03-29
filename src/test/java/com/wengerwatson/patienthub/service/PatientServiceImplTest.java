package com.wengerwatson.patienthub.service;

import com.wengerwatson.patienthub.exception.PatientNotFoundException;
import com.wengerwatson.patienthub.model.Patient;
import com.wengerwatson.patienthub.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService = new PatientServiceImpl(patientRepository);
    }

    @Test
    void findById_ValidId_ReturnsPatient() {
        // Arrange
        Long patientId = 1L;
        Patient expectedPatient = new Patient();
        expectedPatient.setId(patientId);
        when(patientRepository.findById(patientId)).thenReturn(expectedPatient);

        // Act
        Patient actualPatient = patientService.findById(patientId);

        // Assert
        assertEquals(expectedPatient, actualPatient);
        verify(patientRepository, times(1)).findById(patientId);
    }

    @Test
    void findById_InvalidId_ThrowsException() {
        // Arrange
        Long invalidId = 2L;
        when(patientRepository.findById(invalidId)).thenReturn(null);

        // Act & Assert
        assertThrows(PatientNotFoundException.class, () -> patientService.findById(invalidId));
    }

    // Add more test cases for other methods as needed
}
