package com.interview.preparation.low_level_design.interview.doctor_appointment.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Patient {
    private final String id;
    private final String name;

    public Patient( String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
