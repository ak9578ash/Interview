package com.interview.preparation.low_level_design.interview.doctor_appointment;

import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.DoctorNotFoundException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.exception.InvalidSlotException;
import com.interview.preparation.low_level_design.interview.doctor_appointment.model.*;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.AppointmentRepo;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.DoctorRepo;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.PatientRepo;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.impl.AppointmentRepoImpl;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.impl.DoctorRepoImpl;
import com.interview.preparation.low_level_design.interview.doctor_appointment.repository.impl.PatientRepoImpl;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.AppointmentService;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.DoctorService;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.PatientService;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.impl.AppointmentServiceImpl;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.impl.DoctorServiceImpl;
import com.interview.preparation.low_level_design.interview.doctor_appointment.service.impl.PatientServiceImpl;
import com.interview.preparation.low_level_design.interview.doctor_appointment.strategy.impl.DoctorRatingStrategyImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final DoctorRepo doctorRepo = new DoctorRepoImpl();
    private static  final DoctorService doctorService = new DoctorServiceImpl(doctorRepo);

    private static final PatientRepo patientRepo = new PatientRepoImpl();
    private static final PatientService patientService = new PatientServiceImpl(patientRepo,doctorService);

    private static final AppointmentRepo appointmentRepo = new AppointmentRepoImpl();
    private static final AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepo , doctorService);


    public static void main(String[] args) throws DoctorNotFoundException, InvalidSlotException {
        int year = LocalDateTime.now().getYear();
        int months = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();

        Doctor curiousDoctor = new Doctor("curious" , DoctorSpecialization.Cardiologist, 1);
        doctorService.registerDoctor(curiousDoctor);
//        try{
//            doctorService.registerAvailability(curiousDoctor.getId() ,
//                    Arrays.asList(new Slot(LocalDateTime.of(year,months,day,9,0),
//                            LocalDateTime.of(year,months,day,10,30))));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        List<Slot> slotList1 = Arrays.asList(new Slot(LocalDateTime.of(year,months,day,9,0) , LocalDateTime.of(year,months,day,10,0)),
                new Slot(LocalDateTime.of(year,months,day,12,0) , LocalDateTime.of(year,months,day,13,0)),
                new Slot(LocalDateTime.of(year,months,day,16,0) , LocalDateTime.of(year,months,day,17,0)));

        try{
            doctorService.registerAvailability(curiousDoctor.getId() ,slotList1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Doctor dreadfulDoctor = new Doctor("dreadful" , DoctorSpecialization.Dermatologist, 2);
        doctorService.registerDoctor(dreadfulDoctor);

        List<Slot> slotList2 = Arrays.asList(new Slot(LocalDateTime.of(year,months,day,9,0) , LocalDateTime.of(year,months,day,10,0)),
                new Slot(LocalDateTime.of(year,months,day,11,0) , LocalDateTime.of(year,months,day,12,0)),
                new Slot(LocalDateTime.of(year,months,day,13,0) , LocalDateTime.of(year,months,day,14,0)));

        try{
            doctorService.registerAvailability(dreadfulDoctor.getId() ,slotList2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Patient patientA = new Patient("patientA");
        patientService.registerPatient(patientA);

        List<Doctor>availableDoctor = patientService.getAvailableSlots(null , DoctorSpecialization.Cardiologist,new DoctorRatingStrategyImpl());
        for(int i=0;i<availableDoctor.size();i++){
            List<Slot>slots = availableDoctor.get(i).getAvailableSlots();
            String doctorName = availableDoctor.get(i).getName();
            int rating = availableDoctor.get(i).getRating();
            System.out.print(doctorName + " " + rating + ": ");
            for(int j=0;j<slots.size();j++){
                System.out.print("(" + slots.get(j).getStartTime().getHour() + " " + slots.get(j).getEndTime().getHour() + ")" + " ");
            }
            System.out.println();
        }

        Appointment appointmentA = appointmentService.createAppointment(curiousDoctor,patientA,
                new Slot(LocalDateTime.of(year,months,day,12,0) , LocalDateTime.of(year,months,day,13,0)));
        System.out.println(appointmentA.getId());

        availableDoctor = patientService.getAvailableSlots(null , DoctorSpecialization.Cardiologist,new DoctorRatingStrategyImpl());
        for(int i=0;i<availableDoctor.size();i++){
            String doctorName = availableDoctor.get(i).getName();
            List<Slot>slots = availableDoctor.get(i).getAvailableSlots();
            int rating = availableDoctor.get(i).getRating();
            System.out.print(doctorName + " " + rating + ": ");
            for(int j=0;j<slots.size();j++){
                System.out.print("(" + slots.get(j).getStartTime().getHour() + " " + slots.get(j).getEndTime().getHour() + ")" + " ");
            }
            System.out.println();
        }

        appointmentService.cancelAppointment(appointmentA);
        availableDoctor = patientService.getAvailableSlots(null , DoctorSpecialization.Cardiologist,new DoctorRatingStrategyImpl());
        for(int i=0;i<availableDoctor.size();i++){
            String doctorName = availableDoctor.get(i).getName();
            List<Slot>slots = availableDoctor.get(i).getAvailableSlots();
            int rating = availableDoctor.get(i).getRating();
            System.out.print(doctorName + " " + rating + ": ");
            for(int j=0;j<slots.size();j++){
                System.out.print("(" + slots.get(j).getStartTime().getHour() + " " + slots.get(j).getEndTime().getHour() + ")" + " ");
            }
            System.out.println();
        }

        Patient patientB = new Patient("patientB");
        patientService.registerPatient(patientB);

        Appointment appointmentB = appointmentService.createAppointment(curiousDoctor,patientB,
                new Slot(LocalDateTime.of(year,months,day,12,0) , LocalDateTime.of(year,months,day,13,0)));
        System.out.println(appointmentB.getId());

        Doctor daringDoctor = new Doctor("daring" , DoctorSpecialization.Dermatologist, 3);
        doctorService.registerDoctor(daringDoctor);

        List<Slot> slotList3 = Arrays.asList(new Slot(LocalDateTime.of(year,months,day,11,0) , LocalDateTime.of(year,months,day,12,0)),
                new Slot(LocalDateTime.of(year,months,day,14,0) , LocalDateTime.of(year,months,day,15,0)));

        try{
            doctorService.registerAvailability(daringDoctor.getId() ,slotList3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        availableDoctor = patientService.getAvailableSlots(null , DoctorSpecialization.Dermatologist,new DoctorRatingStrategyImpl());
        for(int i=0;i<availableDoctor.size();i++){
            String doctorName = availableDoctor.get(i).getName();
            List<Slot>slots = availableDoctor.get(i).getAvailableSlots();
            int rating = availableDoctor.get(i).getRating();
            System.out.print(doctorName + " " + rating + ": ");
            for(int j=0;j<slots.size();j++){
                System.out.print("(" + slots.get(j).getStartTime().getHour() + " " + slots.get(j).getEndTime().getHour() + ")" + " ");
            }
            System.out.println();
        }

        Patient patientF = new Patient("patientF");
        patientService.registerPatient(patientF);

        Patient patientC = new Patient("patientC");
        patientService.registerPatient(patientC);

        appointmentA = appointmentService.createAppointment(curiousDoctor,patientA,
                new Slot(LocalDateTime.of(year,months,day,12,0) , LocalDateTime.of(year,months,day,13,0)));

        Appointment appointmentF = appointmentService.createAppointment(curiousDoctor,patientF,
                new Slot(LocalDateTime.of(year,months,day,9,0) , LocalDateTime.of(year,months,day,10,0)));

        Appointment appointmentC = appointmentService.createAppointment(curiousDoctor,patientC,
                new Slot(LocalDateTime.of(year,months,day,16,0) , LocalDateTime.of(year,months,day,17,0)));

        availableDoctor = patientService.getAvailableSlots(null , DoctorSpecialization.Cardiologist,new DoctorRatingStrategyImpl());
        if(availableDoctor.size()==0){
            System.out.println("cardiologist is not available");
        }

        List<Appointment>appointmentPatientF = appointmentService.getPatientAppointments(patientF.getId());
        for(int i=0;i<appointmentPatientF.size();i++){
            System.out.println(appointmentPatientF.get(i).getDoctorId());
        }
    }
}
