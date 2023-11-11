package com.interview.preparation.low_level_design.interview.doctor_appointment.service.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.DoctorSpecialization;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Patient;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.PatientRepo;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.DoctorService;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.PatientService;
import com.interview.preparation.low_level_design.interview.doctor_appointment.strategy.DoctorStrategy;

import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final DoctorService doctorService;

    public PatientServiceImpl(PatientRepo patientRepo, DoctorService doctorService) {
        this.patientRepo = patientRepo;
        this.doctorService = doctorService;
    }

    @Override
    public void registerPatient(Patient patient) {
        patientRepo.registerPatient(patient);
        System.out.println("registered " + patient.getName());
    }

    @Override
    public List<Doctor> getAvailableSlots(Slot slot, DoctorSpecialization doctorSpecialization , DoctorStrategy doctorStrategy) {
       List<Doctor> doctorList = doctorService.getAllDoctor();
       List<Doctor> filteredDoctorList = new ArrayList<>();
       for(int i=0;i<doctorList.size();i++){
           if(doctorList.get(i).getDoctorSpecialization().equals(doctorSpecialization) && doctorList.get(i).getAvailableSlots().size()!=0){
               filteredDoctorList.add(doctorList.get(i));
           }
       }
       return doctorStrategy.apply(filteredDoctorList);
    }
}
