package com.interview.preparation.low_level_design.interview.doctor_appointment.repository;

import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Appointment;
import java.util.List;

public interface AppointmentRepo {
    void registerAppointment(Appointment appointment);
    void cancelAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();

}
