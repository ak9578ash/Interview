package com.interview.preparation.low_level_design.interview.doctor_appointment.strategy.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.strategy.DoctorStrategy;

import javax.print.Doc;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DoctorRatingComparator implements Comparator<Doctor>{
    @Override
    public int compare(Doctor d1 , Doctor d2){
        return Integer.compare(d2.getRating(),d1.getRating());
    }
}
public class DoctorRatingStrategyImpl implements DoctorStrategy {
    @Override
    public List<Doctor> apply(List<Doctor> doctorList) {
        Collections.sort(doctorList,new DoctorRatingComparator());
        return doctorList;
    }
}
