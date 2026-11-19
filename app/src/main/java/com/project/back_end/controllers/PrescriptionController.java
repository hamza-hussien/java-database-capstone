
package com.project.back_end.controllers;

import com.project.back_end.services.TokenService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final TokenService tokenService;

    public PrescriptionController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, String>> addPrescription(
            @PathVariable String token,
            @Valid @RequestBody PrescriptionRequest prescription,
            BindingResult bindingResult) {

        Map<String, String> response = new HashMap<>();

        if (!tokenService.validateToken(token)) {
            response.put("message", "Invalid token");
            return ResponseEntity.status(401).body(response);
        }

        if (bindingResult.hasErrors()) {
            response.put("message", "Invalid prescription data");
            return ResponseEntity.badRequest().body(response);
        }

        // Save prescription here using your repository or service.
        response.put("message", "Prescription submitted successfully");

        return ResponseEntity.ok(response);
    }

    public static class PrescriptionRequest {

        private Long patientId;
        private Long doctorId;
        private String medication;
        private String dosage;
        private String instructions;

        public Long getPatientId() {
            return patientId;
        }

        public void setPatientId(Long patientId) {
            this.patientId = patientId;
        }

        public Long getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(Long doctorId) {
            this.doctorId = doctorId;
        }

        public String getMedication() {
            return medication;
        }

        public void setMedication(String medication) {
            this.medication = medication;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }
    }
}

