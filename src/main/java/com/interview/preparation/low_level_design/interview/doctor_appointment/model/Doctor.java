package com.interview.preparation.low_level_design.interview.doctor_appointment.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Doctor {
    private final String id;
    private final String name;
    private final DoctorSpecialization doctorSpecialization;
    private final List<Slot> availableSlots;
    private final int rating;
    public Doctor(String name, DoctorSpecialization doctorSpecialization, int rating){
        this.rating = rating;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.doctorSpecialization = doctorSpecialization;
        this.availableSlots = new ArrayList<>();
    }
}
