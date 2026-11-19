
package com.project.back_end.models;

public class Doctor {

    private int doctorId;
    private int userId;
    private String specialization;
    private String phone;

    public Doctor() {
    }

    public Doctor(int doctorId, int userId,
                  String specialization, String phone) {
        this.doctorId = doctorId;
        this.userId = userId;
        this.specialization = specialization;
        this.phone = phone;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
