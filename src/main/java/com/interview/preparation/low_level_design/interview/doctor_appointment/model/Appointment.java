package com.interview.preparation.low_level_design.interview.doctor_appointment.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Appointment {
    private final String id;
    private final String patientId;
    private final String doctorId;
    private final Slot slot;


    public Appointment(String patientId, String doctorId, Slot slot) {
        this.id = UUID.randomUUID().toString();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.slot = slot;
    }
}
