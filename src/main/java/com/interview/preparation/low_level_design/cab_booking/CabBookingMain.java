package com.interview.preparation.low_level_design.cab_booking;

import com.interview.preparation.low_level_design.cab_booking.exception.BadRequestException;
import com.interview.preparation.low_level_design.cab_booking.exception.CabTemporarilyUnavailable;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;
import com.interview.preparation.low_level_design.cab_booking.model.Payment;
import com.interview.preparation.low_level_design.cab_booking.model.Trip;
import com.interview.preparation.low_level_design.cab_booking.model.account.Address;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import com.interview.preparation.low_level_design.cab_booking.model.account.UserProfile;
import com.interview.preparation.low_level_design.cab_booking.repository.CabRepository;
import com.interview.preparation.low_level_design.cab_booking.repository.PaymentRepository;
import com.interview.preparation.low_level_design.cab_booking.repository.TripRepository;
import com.interview.preparation.low_level_design.cab_booking.repository.UserRepository;
import com.interview.preparation.low_level_design.cab_booking.service.*;
import com.interview.preparation.low_level_design.cab_booking.strategies.CabMatchingStrategy;
import com.interview.preparation.low_level_design.cab_booking.strategies.DefaultCabMatchingStrategy;
import com.interview.preparation.low_level_design.cab_booking.strategies.DefaultPricingStrategy;
import com.interview.preparation.low_level_design.cab_booking.strategies.PriceStrategy;
import com.interview.preparation.low_level_design.cab_booking.utils.CabLockProvider;
import com.interview.preparation.low_level_design.cab_booking.utils.InMemoryCabLockProvider;

import java.util.ArrayList;
import java.util.List;


public class CabBookingMain {
    public static UserRepository userRepository;
    public static UserService userService;

    public static CabRepository cabRepository;
    public static CabService cabService;
    public static CabAvailabilityService cabAvailabilityService;

    public static TripRepository tripRepository;
    public static TripService tripService;

    public static CabLockProvider cabLockProvider;

    public static CabMatchingStrategy cabMatchingStrategy;

    public static PriceStrategy priceStrategy;

    public static PaymentRepository paymentRepository;
    public static PaymentService paymentService;

    public static void main(String[] args) throws CabTemporarilyUnavailable, BadRequestException {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        cabRepository = new CabRepository();
        cabService = new CabService(cabRepository);

        cabLockProvider = new InMemoryCabLockProvider(100);

        cabMatchingStrategy = new DefaultCabMatchingStrategy();

        priceStrategy = new DefaultPricingStrategy();

        tripRepository = new TripRepository();
        tripService = new TripService(tripRepository, cabLockProvider, cabMatchingStrategy, priceStrategy);

        cabAvailabilityService = new CabAvailabilityService(tripService , cabLockProvider,cabService);

        paymentRepository = new PaymentRepository();
        paymentService = new PaymentService(paymentRepository , 0 ,cabLockProvider);
// ---------------------------------------------------------------------------------------------------------------------
        Address user1Address = new Address("82 A Madhav kunj",
                "Pratap Nagar", "Agra", "UP", "282002");
        UserProfile user1Profile = new UserProfile("Akash", "",
                "Gupta", "akashgupta9578@gmail.com", "7906048908");
        User user1 = new User(user1Address, user1Profile);
        userService.addUser(user1);

        Address user2Address = new Address("63 B Keshav kunj",
                "Jaipur Houser", "Agra", "UP", "282002");
        UserProfile user2Profile = new UserProfile("Aman", "",
                "Gupta", "amangupta@gmail.com", "1234567890");
        User user2 = new User(user2Address, user2Profile);
        userService.addUser(user2);
// ---------------------------------------------------------------------------------------------------------------------
        Address driverAddress1 = new Address("63 B Keshav kunj",
                "Jaipur Houser", "Agra", "UP", "282002");
        UserProfile driverProfile1 = new UserProfile("xyz", "",
                "abc", "xyzabc@gmail.com", "999999999");
        User driver1 = new User(driverAddress1, driverProfile1);
        userService.addUser(driver1);

        Address driverAddress2 = new Address("63 B Keshav kunj",
                "Jaipur Houser", "Agra", "UP", "282002");
        UserProfile driverProfile2 = new UserProfile("xyz", "",
                "abc", "xyzabc@gmail.com", "999999999");
        User driver2 = new User(driverAddress2, driverProfile2);
        userService.addUser(driver2);

        Address driverAddress3 = new Address("63 B Keshav kunj",
                "Jaipur Houser", "Agra", "UP", "282002");
        UserProfile driverProfile3 = new UserProfile("xyz", "",
                "abc", "xyzabc@gmail.com", "999999999");
        User driver3 = new User(driverAddress3, driverProfile3);
        userService.addUser(driver3);

        Address driverAddress4 = new Address("63 B Keshav kunj",
                "Jaipur Houser", "Agra", "UP", "282002");
        UserProfile driverProfile4 = new UserProfile("xyz", "",
                "abc", "xyzabc@gmail.com", "999999999");
        User driver4 = new User(driverAddress4, driverProfile4);
        userService.addUser(driver4);


        Cab cab1 = new Cab("UP80 CD 1234", driver1);
        cab1.setCurrentLocation(new Location(1.0, 1.0));
        cabService.addCab(cab1);

        Cab cab2 = new Cab("UP80 AB 3456", driver2);
        cab2.setCurrentLocation(new Location(2.0, 2.0));
        cabService.addCab(cab2);

        Cab cab3 = new Cab("UP80 CF 1234", driver3);
        cab3.setCurrentLocation(new Location(3.0, 3.0));
        cabService.addCab(cab3);

        Cab cab4 = new Cab("UP80 FE 3456", driver4);
        cab4.setCurrentLocation(new Location(4.0, 4.0));
        cabService.addCab(cab4);

        // TEST CASE 1
//        List<Cab> user1AvailableCabs = cabAvailabilityService.getAvailableCabs();
//        List<Cab> user1SelectedCabs = new ArrayList<>();
//        user1SelectedCabs.add(user1AvailableCabs.get(0));
//        user1SelectedCabs.add(user1AvailableCabs.get(1));
//
//        Trip user1Trip = tripService.addTrip(user1 , user1SelectedCabs,
//                new Location(1.0,1.0) ,new Location(2.0,2.0));
//        Payment user1Payment = new Payment(user1Trip);
//        paymentService.makePayment(user1Payment , user1);
//        tripService.confirmTrip(user1Trip,user1);
//
//       // after some time user1 can end his/her trip
//        tripService.endTrip(user1Trip,user1);
//
//        List<Cab> user2AvailableCabs = cabAvailabilityService.getAvailableCabs();

        // TEST CASE 2
//        List<Cab> user1AvailableCabs = cabAvailabilityService.getAvailableCabs();
//        List<Cab> user1SelectedCabs = new ArrayList<>();
//        user1SelectedCabs.add(user1AvailableCabs.get(0));
//        user1SelectedCabs.add(user1AvailableCabs.get(1));
//
//        Trip user1Trip = tripService.addTrip(user1 , user1SelectedCabs,
//                new Location(1.0,1.0) ,new Location(2.0,2.0));
//        Payment user1Payment = new Payment(user1Trip);
//        paymentService.addToPaymentFailure(user1Payment);
//        paymentService.processFailedPayment(user1Payment , user1);
//
//        List<Cab> user2AvailableCabs = cabAvailabilityService.getAvailableCabs();

        // TEST CASE 3
//        List<Cab> user1AvailableCabs = cabAvailabilityService.getAvailableCabs();
//        List<Cab> user2AvailableCabs = cabAvailabilityService.getAvailableCabs();
//
//        List<Cab> user1SelectedCabs = new ArrayList<>();
//        user1SelectedCabs.add(user1AvailableCabs.get(0));
//        user1SelectedCabs.add(user1AvailableCabs.get(1));
//
//        Trip user1Trip = tripService.addTrip(user1 , user1SelectedCabs,
//                new Location(1.0,1.0) ,new Location(2.0,2.0));
//
//
//        List<Cab> user2SelectedCabs = new ArrayList<>();
//        user2SelectedCabs.add(user2AvailableCabs.get(0));
//
//        Trip user2Trip = tripService.addTrip(user2 , user2SelectedCabs,
//                new Location(1.0,1.0) ,new Location(2.0,2.0));

    }
}
