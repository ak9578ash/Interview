package com.interview.preparation.low_level_design.interview.doctor_appointment.repository.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Appointment;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.AppointmentRepo;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepoImpl implements AppointmentRepo {
    List<Appointment>appointmentList = new ArrayList<>();

    @Override
    public void registerAppointment(Appointment appointment) {
        this.appointmentList.add(appointment);
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        appointmentList.remove(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointmentList;
    }

}
