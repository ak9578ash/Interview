package com.interview.preparation.low_level_design.interview.doctor_appointment.service.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.InvalidSlotException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.DoctorRepo;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.DoctorService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public void registerDoctor(Doctor doctor) {
        doctorRepo.registerDoctor(doctor);
        System.out.println("welcome " + doctor.getName() + " doctor");
    }

    @Override
    public void registerAvailability(String doctorId, List<Slot> slots) throws DoctorNotFoundException, InvalidSlotException {
        for(int i=0;i<slots.size();i++){
            Slot slot = slots.get(i);
            if(slot.getStartTime().until(slot.getEndTime(), ChronoUnit.MINUTES) > 60){
                throw new InvalidSlotException("slots can only be of 60 minutes");
            }
        }
        doctorRepo.registerAvailability(doctorId,slots);
        System.out.println("registered");
    }

    @Override
    public void cancelAvailability(String doctorId, Slot slot) throws DoctorNotFoundException {
        doctorRepo.cancelAvailability(doctorId,slot);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepo.getAllDoctor();
    }
}
