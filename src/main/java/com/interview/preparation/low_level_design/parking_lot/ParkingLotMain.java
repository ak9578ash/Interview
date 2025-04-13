package com.interview.preparation.low_level_design.parking_lot;

import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingFloorException;
import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingLotException;
import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingSpotException;
import com.interview.preparation.low_level_design.parking_lot.exception.NoParkingSpotAvailableException;
import com.interview.preparation.low_level_design.parking_lot.model.account.*;
import com.interview.preparation.low_level_design.parking_lot.model.parking.*;
import com.interview.preparation.low_level_design.parking_lot.model.vehicle.*;
import com.interview.preparation.low_level_design.parking_lot.repository.AdminRepository;
import com.interview.preparation.low_level_design.parking_lot.repository.ParkingLotRepository;
import com.interview.preparation.low_level_design.parking_lot.repository.PaymentRepository;
import com.interview.preparation.low_level_design.parking_lot.service.AdminService;
import com.interview.preparation.low_level_design.parking_lot.service.ParkingLotService;
import com.interview.preparation.low_level_design.parking_lot.service.PaymentService;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingLotMain {
    static ParkingLotRepository parkingLotRepository;
    static ParkingLotService parkingLotService;
    static AdminRepository adminRepository;
    static AdminService adminService;
    static PaymentRepository paymentRepository;
    static PaymentService paymentService;

    public static void main(String[] args) throws InvalidParkingFloorException, InvalidParkingLotException, NoParkingSpotAvailableException, InvalidParkingSpotException {
        parkingLotRepository = new ParkingLotRepository();
        parkingLotService = new ParkingLotService(parkingLotRepository);

        adminRepository = new AdminRepository();
        adminService = new AdminService(adminRepository);

        paymentRepository = new PaymentRepository();
        paymentService = new PaymentService(paymentRepository);

        Address adminAddress = new Address("Admin Address", "", "",
                "BG Road", "Bangalore", "Karnataka", "560075");

        // admin creation --> TEST CASE1
        Account adminAccount = new Admin(parkingLotService);
        adminAccount.setId(UUID.randomUUID().toString());
        adminAccount.setEmail("admin@gmail.com");
        adminAccount.setUserName("admin");
        adminAccount.setPassword("password");
        adminAccount.setLastAccessed(LocalDateTime.now());
        adminAccount.setContact(new Contact("1234567890", adminAddress,
                new PersonalInfo("Akash", "Gupta", "", "Mr.", "14-02-1998")));

        // added the admin to the list of admin
        adminService.addAdmin((Admin) adminAccount);

        // parkingLot creation
        Address parkingLot1Address = new Address("Parking Lot Address", "", "",
                "BG Road", "Bangalore", "Karnataka", "560075");
        ParkingLot parkingLot1 = ParkingLot.getInstance(parkingLot1Address);

        // added the parkingLot to the list by admin
        ((Admin) adminAccount).addParkingLot(parkingLot1);

        // adding the multiple floors to parkingLot by admin --> TEST CASE2
        ((Admin) adminAccount).addParkingFloor(parkingLot1.getParkingLotId(), new ParkingFloor("floor_id1"));
        ((Admin) adminAccount).addParkingFloor(parkingLot1.getParkingLotId(), new ParkingFloor("floor_id2"));

        // adding the entrance gate to parkingLot by admin --> TEST CASE 3
        ((Admin) adminAccount).addEntranceGate(parkingLot1.getParkingLotId(), new EntranceGate("entrance_gate1"));

        // adding the exit gate to parkingLot by admin --> TEST CASE 4
        ((Admin) adminAccount).addExitGate(parkingLot1.getParkingLotId(), new ExitGate("exit_gate1"));

        // adding the multiple parking spots by admin --> TEST CASE 5
        String parkingFloorId = parkingLot1.getParkingFloors().get(0).getParkingFloorId();
        ((Admin) adminAccount).addParkingSpot(parkingLot1.getParkingLotId(), parkingFloorId, new CarParkingSpot("car1"));
        ((Admin) adminAccount).addParkingSpot(parkingLot1.getParkingLotId(), parkingFloorId, new CarParkingSpot("car2"));
        ((Admin) adminAccount).addParkingSpot(parkingLot1.getParkingLotId(), parkingFloorId, new ElectricBikeParkingSpot("electric_bike1"));
        ((Admin) adminAccount).addParkingSpot(parkingLot1.getParkingLotId(), parkingFloorId, new LargeVehicleParkingSpot("large_vehicle1"));

        //check for the availability of CAR parking in the parking lot --> TEST CASE 6
        System.out.println(parkingLot1.canPark(VehicleType.CAR));

        // check for the availability of MOTORBIKE parking in the parking lot --> TEST CASE 7
        System.out.println(parkingLot1.canPark(VehicleType.MOTORBIKE));

        // check is the parkingLot full or not --> TEST CASE 8
        System.out.println(parkingLot1.isFull());

        // get parkingSpot for Car --> TEST CASE 9
        Vehicle car1 = new Car("UP80-CD-6113");
        ParkingSpot carParkingSpot1 = parkingLot1.getParkingSpot(car1.getType());
        System.out.println(carParkingSpot1.getParkingSpotType());
        System.out.println(carParkingSpot1.getParkingSpotId());

        Vehicle car2 = new Car("UP80-AB-1234");
        ParkingSpot carParkingSpot2 = parkingLot1.getParkingSpot(car2.getType());
        System.out.println(carParkingSpot2.getParkingSpotType());
        System.out.println(carParkingSpot2.getParkingSpotId());

        // should not able to get the parking spot --> TEST CASE 10
//        Vehicle car3 = new Car("UP80-EF-1234");
//        ParkingSpot carParkingSpot3= parkingLot1.getParkingSpot(car3.getType());
//        System.out.println(carParkingSpot3.getParkingSpotType());
//        System.out.println(carParkingSpot3.getParkingSpotId());

        // should not able to get the parking spot --> TEST CASE 11
//        Vehicle motorbike1 = new MotorBike("UP80-AB-6789");
//        ParkingSpot motorbikeParkingSpot1 = parkingLot1.getParkingSpot(motorbike1.getType());
//        System.out.println(motorbikeParkingSpot1.getParkingSpotType());
//        System.out.println(motorbikeParkingSpot1.getParkingSpotId());

        // should be able to get the parkingTicket --> TEST CASE 12
        String parkingFloorId1 = parkingLot1.getParkingFloors().get(1).getParkingFloorId();
        ((Admin) adminAccount).addParkingSpot(parkingLot1.getParkingLotId(), parkingFloorId1, new CarParkingSpot("car3"));
        ParkingTicket parkingTicketCar1 = parkingLot1.getEntranceGates().get(0).getParkingTicket(parkingLot1, car1);
        System.out.println(parkingTicketCar1.getAllocatedSpotId());

        // should not be able to get the parkingTicket --> TEST CASE 13
        Vehicle car4 = new Car("UP80-qw-1234");
        try {
            ParkingTicket parkingTicketCar4 = parkingLot1.getEntranceGates().get(0).getParkingTicket(parkingLot1, car4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // should be able to  get the ticket --> TEST CASE 14
        Vehicle van1 = new Van("UP80-as-1234");
        ParkingTicket vanParkingTicket1 = parkingLot1.getEntranceGates().get(0).getParkingTicket(parkingLot1, van1);
        System.out.println(vanParkingTicket1.getTicketNumber());
        System.out.println(vanParkingTicket1.getAllocatedSpotId());

        // should not able to get the ticket --> TEST CASE 15
        Vehicle van2 = new Van("UP80-as-1234");
        ParkingTicket vanParkingTicket2 = null;
        try {
            vanParkingTicket2 = parkingLot1.getEntranceGates().get(0).getParkingTicket(parkingLot1, van2);
            System.out.println(vanParkingTicket2.getTicketNumber());
            System.out.println(vanParkingTicket2.getAllocatedSpotId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //vacate a parkingSpot --> TEST CASE 16
        ParkingTicket vacatedParkingTicket = parkingLot1.getExitGates().get(0).scanAndVacate(parkingLot1, vanParkingTicket1);
        System.out.println(vacatedParkingTicket.getCharges());

        // should be able to get the ticket after the vacating a spot --> TEST CASE 16
        try {
            vanParkingTicket2 = parkingLot1.getEntranceGates().get(0).getParkingTicket(parkingLot1, van2);
            System.out.println(vanParkingTicket2.getTicketNumber());
            System.out.println(vanParkingTicket2.getAllocatedSpotId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // make payment --> TEST CASE 17
        Payment bill = paymentService.makePayment(vanParkingTicket1.getTicketNumber(),
                new Payment(vanParkingTicket1.getTicketNumber(), vacatedParkingTicket.getCharges()));

        System.out.println(bill.getAmount());
        System.out.println(bill.getStatus());
    }
}
