
package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private final List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getDoctorId() == id)
                .findFirst()
                .orElse(null);
    }

    public Doctor addDoctor(Doctor doctor) {
        doctors.add(doctor);
        return doctor;
    }

    public Doctor updateDoctor(int id, Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId() == id) {
                doctors.set(i, updatedDoctor);
                return updatedDoctor;
            }
        }
        return null;
    }

    public boolean deleteDoctor(int id) {
        return doctors.removeIf(
                doctor -> doctor.getDoctorId() == id);
    }
}
