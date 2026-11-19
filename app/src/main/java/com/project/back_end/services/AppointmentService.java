
package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public Appointment getAppointmentById(int id) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId() == id)
                .findFirst()
                .orElse(null);
    }

    public Appointment addAppointment(Appointment appointment) {
        appointments.add(appointment);
        return appointment;
    }

    public Appointment updateAppointment(
            int id, Appointment updatedAppointment) {

        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId() == id) {
                appointments.set(i, updatedAppointment);
                return updatedAppointment;
            }
        }

        return null;
    }

    public boolean deleteAppointment(int id) {
        return appointments.removeIf(
                appointment -> appointment.getAppointmentId() == id);
    }
}
