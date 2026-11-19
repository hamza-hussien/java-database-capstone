
package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenService tokenService;

    public DoctorController(
            DoctorService doctorService,
            TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable int id,
            @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDoctor(@PathVariable int id) {
        return doctorService.deleteDoctor(id);
    }

    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, Object>> getAvailability(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        Map<String, Object> response = new HashMap<>();

        if (!tokenService.validateToken(token)) {
            response.put("success", false);
            response.put("message", "Invalid token");
            return ResponseEntity.status(401).body(response);
        }

        try {
            LocalDate requestedDate = LocalDate.parse(date);

            Doctor doctor = doctorService.getDoctorById(
                    doctorId.intValue());

            if (doctor == null) {
                response.put("success", false);
                response.put("message", "Doctor not found");
                return ResponseEntity.status(404).body(response);
            }

            response.put("success", true);
            response.put("user", user);
            response.put("doctorId", doctorId);
            response.put("date", requestedDate);
            response.put("availableTimes", doctor.getAvailableTimes());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Invalid date or request");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
