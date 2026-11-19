
package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final List<Prescription> prescriptions = new ArrayList<>();

    public static class Prescription {
        private int prescriptionId;
        private int patientId;
        private int doctorId;
        private String medication;
        private String dosage;
        private String instructions;

        public Prescription() {
        }

        public int getPrescriptionId() {
            return prescriptionId;
        }

        public void setPrescriptionId(int prescriptionId) {
            this.prescriptionId = prescriptionId;
        }

        public int getPatientId() {
            return patientId;
        }

        public void setPatientId(int patientId) {
            this.patientId = patientId;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
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

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    @PostMapping
    public Prescription addPrescription(
            @RequestBody Prescription prescription) {
        prescriptions.add(prescription);
        return prescription;
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable int id) {
        return prescriptions.stream()
                .filter(p -> p.getPrescriptionId() == id)
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deletePrescription(@PathVariable int id) {
        boolean removed = prescriptions.removeIf(
                p -> p.getPrescriptionId() == id);

        return removed
                ? "Prescription deleted successfully"
                : "Prescription not found";
    }
}
