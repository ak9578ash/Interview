package com.interview.preparation.low_level_design.interview.doctor_appointment.service.impl;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.InvalidSlotException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Appointment;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Doctor;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Patient;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.Slot;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.AppointmentRepo;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.AppointmentService;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.DoctorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DoctorService doctorService;

    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, DoctorService doctorService) {
        this.appointmentRepo = appointmentRepo;
        this.doctorService = doctorService;
    }

    @Override
    public Appointment createAppointment(Doctor doctor, Patient patient, Slot slot) throws DoctorNotFoundException {
        Appointment appointment = new Appointment(patient.getId(), doctor.getId(), slot);
        doctorService.cancelAvailability(doctor.getId(),slot);
        appointmentRepo.registerAppointment(appointment);
        System.out.println("appointment created");
        return appointment;
    }

    @Override
    public void cancelAppointment(Appointment appointment) throws DoctorNotFoundException, InvalidSlotException {
        appointmentRepo.cancelAppointment(appointment);
        doctorService.registerAvailability(appointment.getDoctorId() , Arrays.asList(appointment.getSlot()));
        System.out.println("appointment canceled");
    }

    @Override
    public List<Appointment> getDoctorAppointments(String doctorId) {
        List<Appointment>appointmentList = appointmentRepo.getAllAppointments();
        List<Appointment>filteredAppointmentList = new ArrayList<>();

        for(int i=0;i<appointmentList.size();i++){
            if(appointmentList.get(i).getDoctorId().equals(doctorId)){
                filteredAppointmentList.add(appointmentList.get(i));
            }
        }
        return filteredAppointmentList;
    }

    @Override
    public List<Appointment> getPatientAppointments(String patientId) {
        List<Appointment>appointmentList = appointmentRepo.getAllAppointments();
        List<Appointment>filteredAppointmentList = new ArrayList<>();

        for(int i=0;i<appointmentList.size();i++){
            if(appointmentList.get(i).getPatientId().equals(patientId)){
                filteredAppointmentList.add(appointmentList.get(i));
            }
        }
        return filteredAppointmentList;
    }
}
