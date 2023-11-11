package com.interview.preparation.low_level_design.interview.doctor_appointment.repository.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.DoctorSpecialization;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Patient;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.PatientRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepoImpl implements PatientRepo {
    List<Patient>patientList = new ArrayList<>();
    Map<String , Patient> patientMap = new HashMap<>();

    @Override
    public void registerPatient(Patient patient) {
        this.patientList.add(patient);
        this.patientMap.put(patient.getId() , patient);
    }
}
