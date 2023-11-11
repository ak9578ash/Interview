package com.interview.preparation.low_level_design.interview.doctor_appointment.repository;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;

import java.util.List;

public interface DoctorRepo {
    void registerDoctor(Doctor doctor);
    void registerAvailability(String doctorId , List<Slot> slots) throws DoctorNotFoundException;
    void cancelAvailability(String doctorId , Slot slot) throws DoctorNotFoundException;
    List<Doctor> getAllDoctor();
}
