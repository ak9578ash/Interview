package com.interview.preparation.low_level_design.interview.doctor_appointment.strategy;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;

import java.util.List;

public interface DoctorStrategy {
    List<Doctor> apply(List<Doctor>doctorList);
}
