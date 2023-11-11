package com.interview.preparation.low_level_design.interview.doctor_appointment.service;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.InvalidSlotException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Appointment;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Patient;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Doctor doctor , Patient patient , Slot slot) throws DoctorNotFoundException;
    void cancelAppointment(Appointment appointment) throws DoctorNotFoundException, InvalidSlotException;
    List<Appointment> getDoctorAppointments(String doctorId);
    List<Appointment> getPatientAppointments(String patientId);
}
