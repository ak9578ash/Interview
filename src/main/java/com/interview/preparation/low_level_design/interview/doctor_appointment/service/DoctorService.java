package com.interview.preparation.low_level_design.interview.doctor_appointment.service;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.InvalidSlotException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;
import java.util.List;


public interface DoctorService {
    void registerDoctor(Doctor doctor);
    void registerAvailability(String doctorId , List<Slot> slots) throws DoctorNotFoundException, InvalidSlotException;
    void cancelAvailability(String doctorId , Slot slot) throws DoctorNotFoundException;
    List<Doctor> getAllDoctor();
}
