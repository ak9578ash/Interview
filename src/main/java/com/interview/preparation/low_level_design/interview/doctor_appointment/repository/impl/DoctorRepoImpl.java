package com.interview.preparation.low_level_design.interview.doctor_appointment.repository.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.DoctorRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepoImpl implements DoctorRepo {
    List<Doctor>doctorList = new ArrayList<>();
    Map<String , Doctor>doctorMap = new HashMap<>();

    @Override
    public void registerDoctor(Doctor doctor) {
        this.doctorMap.put(doctor.getId(),  doctor);
        this.doctorList.add(doctor);
    }

    @Override
    public void registerAvailability(String doctorId, List<Slot> slots) throws DoctorNotFoundException {
        if(doctorMap.containsKey(doctorId)){
            Doctor doctor = doctorMap.get(doctorId);
            doctor.getAvailableSlots().addAll(slots);
            return ;
        }
        throw new DoctorNotFoundException("doctor does not exist");
    }

    @Override
    public void cancelAvailability(String doctorId, Slot slot) throws DoctorNotFoundException {
        if(doctorMap.containsKey(doctorId)){
            Doctor doctor = doctorMap.get(doctorId);
            for(int i=0;i<doctor.getAvailableSlots().size();i++){
                if(doctor.getAvailableSlots().get(i).equals(slot)){
                    doctor.getAvailableSlots().remove(slot);
                    break;
                }
            }
            return ;
        }
        throw new DoctorNotFoundException("doctor does not exist");
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorList;
    }
}
