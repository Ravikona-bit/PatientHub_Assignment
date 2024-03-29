package com.wengerwatson.patienthub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wengerwatson.patienthub.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void getPatientById_ValidId_ReturnsPatient() throws Exception {
        // Arrange
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        when(patientService.findById(patientId)).thenReturn(patient);

        // Act & Assert
        mockMvc.perform(get("/patients/{id}", patientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patientId));
    }

    @Test
    void createPatient_ValidPatient_ReturnsCreated() throws Exception {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setPhoneNumber(Integer.parseInt("1234567890"));
        patient.setEmail("john@example.com");
        String requestBody = objectMapper.writeValueAsString(patient);

        // Mocking the service method
        when(patientService.save(any(Patient.class))).thenReturn(patient);

        // Act & Assert
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.name").value(patient.getName()))
                .andExpect(jsonPath("$.age").value(patient.getAge()))
                .andExpect(jsonPath("$.phoneNumber").value(patient.getPhoneNumber()))
                .andExpect(jsonPath("$.email").value(patient.getEmail()));
    }

    // Add more integration test cases for other endpoints as needed
}
