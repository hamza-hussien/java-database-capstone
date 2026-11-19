
package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.models.Login;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final List<Doctor> doctors = new ArrayList<>();

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(
            TokenService tokenService,
            PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    // Get doctor by ID
    public Doctor getDoctorById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() != null
                        && doctor.getId().equals((long) id))
                .findFirst()
                .orElse(null);
    }

    // Add a doctor
    public Doctor addDoctor(Doctor doctor) {
        doctors.add(doctor);
        return doctor;
    }

    // Update a doctor
    public Doctor updateDoctor(int id, Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() != null
                    && doctors.get(i).getId().equals((long) id)) {
                doctors.set(i, updatedDoctor);
                return updatedDoctor;
            }
        }
        return null;
    }

    // Delete a doctor
    public boolean deleteDoctor(int id) {
        return doctors.removeIf(doctor ->
                doctor.getId() != null
                        && doctor.getId().equals((long) id));
    }

    // Get available times for a specific doctor and date
    public List<String> getAvailableTimes(
            Long doctorId, LocalDate date) {

        Doctor doctor = getDoctorById(doctorId.intValue());

        if (doctor == null || doctor.getAvailableTimes() == null) {
            return new ArrayList<>();
        }

        return doctor.getAvailableTimes().stream()
                .filter(time -> time.startsWith(date.toString()))
                .collect(Collectors.toList());
    }

    // Validate doctor login credentials
    public ResponseEntity<Map<String, String>> validateDoctorLogin(
            Login login) {

        Map<String, String> response = new HashMap<>();

        if (login == null
                || login.getEmail() == null
                || login.getPassword() == null) {

            response.put("message",
                    "Email and password are required");

            return ResponseEntity.badRequest().body(response);
        }

        Doctor doctor = doctors.stream()
                .filter(d -> login.getEmail().equals(d.getEmail()))
                .findFirst()
                .orElse(null);

        if (doctor == null
                || !passwordEncoder.matches(
                        login.getPassword(),
                        doctor.getPassword())) {

            response.put("message",
                    "Invalid email or password");

            return ResponseEntity.status(401).body(response);
        }

        String token = tokenService.generateToken(
                login.getEmail());

        response.put("message", "Login successful");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}

