package com.interview.preparation.low_level_design.interview.doctor_appointment.repository;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.DoctorSpecialization;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Patient;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;

import java.util.List;

public interface PatientRepo {
    void registerPatient(Patient patient);
}
