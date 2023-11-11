package com.interview.preparation.low_level_design.interview.doctor_appointment.service;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.*;
import com.interview.preparation.low_level_design.interview.doctor_appointment.strategy.DoctorStrategy;

import java.util.List;

public interface PatientService {
     void registerPatient(Patient patient);
     List<Doctor> getAvailableSlots(Slot slot , DoctorSpecialization doctorSpecialization , DoctorStrategy doctorStrategy);
}
