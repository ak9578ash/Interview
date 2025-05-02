package com.interview.preparation.low_level_design.movie_ticket_booking;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.*;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.*;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.UserProfile;
import com.interview.preparation.low_level_design.movie_ticket_booking.repository.*;
import com.interview.preparation.low_level_design.movie_ticket_booking.service.*;
import com.interview.preparation.low_level_design.movie_ticket_booking.utils.InMemorySeatLockProvider;
import com.interview.preparation.low_level_design.movie_ticket_booking.utils.SeatLockProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieTicketBookingMain {
    public static UserRepository userRepository;
    public static UserService userService;

    public static TheatreRepository theatreRepository;
    public static TheatreService theatreService;

    public static ShowRepository showRepository;
    public static ShowService showService;

    public static SeatLockProvider seatLockProvider;

    public static BookingRepository bookingRepository;
    public static BookingService bookingService;

    public static SeatAvailabilityService seatAvailabilityService;

    public static PaymentRepository paymentRepository;
    public static PaymentService paymentService;

    public static void main(String[] args) throws InvalidTheatreException, InvalidScreenException, SeatsPermanentlyUnavailableException, SeatTemporarilyUnavailableException, InvalidBookingStatus, BadRequestException {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        theatreRepository = new TheatreRepository();
        theatreService = new TheatreService(theatreRepository);

        showRepository = new ShowRepository();
        showService = new ShowService(showRepository);

        seatLockProvider = new InMemorySeatLockProvider(1000);

        bookingRepository = new BookingRepository();
        bookingService = new BookingService(bookingRepository , seatLockProvider);

        seatAvailabilityService = new SeatAvailabilityService(bookingService , seatLockProvider);

        paymentRepository = new PaymentRepository();
        paymentService = new PaymentService(paymentRepository,0,seatLockProvider);

        Address user1Address = new Address("82 A Madhav kunj",
                "Pratap Nagar","Agra","UP","282002");
        UserProfile user1Profile = new UserProfile("Akash","",
                "Gupta","akashgupta9578@gmail.com","7906048908");
        User user1 = new User(user1Address , user1Profile);
        userService.addUser(user1);

        Address user2Address = new Address("63 B Keshav kunj",
                "Jaipur Houser","Agra","UP","282002");
        UserProfile user2Profile = new UserProfile("Aman","",
                "Gupta","amangupta@gmail.com","1234567890");
        User user2 = new User(user2Address , user2Profile);
        userService.addUser(user2);

        Address theatreAddress = new Address("line_1",
                "line_2","agra","UP","282003");

        Movie movie1 = new Movie("insidious");
        Movie movie2 = new Movie("Avengers");

        Theatre theatre1 = new Theatre("Talkies", theatreAddress);
        Screen screen1 = new Screen("SCREEN 1");
        Screen screen2 = new Screen("SCREEN 2");

        Seat seat1 = new Seat("1","A", 100.0, SeatType.PREMIUM);
        Seat seat2 = new Seat("1","B", 50.0, SeatType.ECONOMY);
        Seat seat3 = new Seat("2","A", 50.0, SeatType.ECONOMY);
        Seat seat4 = new Seat("2","B", 20.0, SeatType.NORMAL);

        theatreService.addTheatre(theatre1);
        theatreService.addScreen(screen1);
        theatreService.addScreen(screen2);
        theatreService.addSeat(seat1);
        theatreService.addSeat(seat2);
        theatreService.addSeat(seat3);
        theatreService.addSeat(seat4);

        theatreService.addScreenInTheatre(theatre1, screen1);
        theatreService.addScreenInTheatre(theatre1, screen2);
        theatreService.addSeatInScreen(screen1, seat1);
        theatreService.addSeatInScreen(screen1, seat2);
        theatreService.addSeatInScreen(screen1, seat3);
        theatreService.addSeatInScreen(screen1, seat4);

        Show show1 = new Show(movie1, screen1, theatre1, LocalDateTime.now(),2*60);
        Show show2 = new Show(movie2, screen2, theatre1, LocalDateTime.now().plusHours(2),2*60);

        showService.addShow(show1);
        showService.addShow(show2);

//        printAllShowsGroupByTheatre();

        // TEST CASE 1
//        List<Seat> user1AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show1);
//        printAvailableSeats(user1AvailableSeats);
//        List<Seat> user1SelectedSeats = new ArrayList<>();
//        user1SelectedSeats.add(user1AvailableSeats.get(0));
//        user1SelectedSeats.add(user1AvailableSeats.get(1));
//
//        Booking user1Booking = bookingService.addBooking(user1 , show1 , user1SelectedSeats);
//        Payment user1Payment = generatePayment(user1Booking);
//        paymentService.makePayment(user1Booking, user1Payment);
//        bookingService.confirmBooking(user1Booking ,user1);
//
//        List<Seat> user2AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show1);
//        System.out.println("-----------");
//        printAvailableSeats(user2AvailableSeats);

        // TEST CASE 2
//        List<Seat> user1AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show);
//        List<Seat> user2AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show);
//
//        List<Seat> user1SelectedSeats = new ArrayList<>();
//        user1SelectedSeats.add(user1AvailableSeats.get(0));
//        user1SelectedSeats.add(user1AvailableSeats.get(1));
//
//        Booking user1Booking = bookingService.addBooking(user1 , show , user1SelectedSeats);
//
//        user2AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show);
//
//        // to demonstrate failed payment case we have to set the allowed retry count in paymentService to zero
//        paymentService.addToFailureBooking(user1Booking);
//        paymentService.processPaymentFailed(user1Booking , user1);
//
//        user2AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show);

        // TEST CASE 3
//        List<Seat> user1AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show);
//        List<Seat> user2AvailableSeats = seatAvailabilityService.getAvailableSeatsOfShow(show);
//
//        List<Seat> user1SelectedSeats = new ArrayList<>();
//        user1SelectedSeats.add(user1AvailableSeats.get(0));
//        user1SelectedSeats.add(user1AvailableSeats.get(1));
//
//        Booking user1Booking = bookingService.addBooking(user1 , show , user1SelectedSeats);
//
//        List<Seat>user2SelectedSeats = new ArrayList<>();
//        user2SelectedSeats.add(user2AvailableSeats.get(0));
//
//        Booking user2Booking = bookingService.addBooking(user2 , show , user2SelectedSeats);

    }

    private static void printAllShowsGroupByTheatre() {
        Map<Theatre, List<Show>> theatreToShowMap = showService.getShowGroupByTheatre();
        for(Map.Entry<Theatre, List<Show>> entry : theatreToShowMap.entrySet()){
            System.out.println("Theatre: " + entry.getKey().getName());
            List<Show> shows = entry.getValue();
            for(Show show : shows) {
                System.out.println("Movie: " + show.getMovie().getName());
                System.out.println("Show Time: " + show.getShowTime());
                System.out.println("Duration: " + show.getShowDuration() + " minutes");
                System.out.println("Screen: " + show.getScreen().getName());
                System.out.println("-----------------------------");
            }
        }
    }

    private static Payment generatePayment(Booking userBooking) {
        Double charges = 0.0;
        for(int i=0;i<userBooking.getBookedSeats().size();i++) {
            charges += userBooking.getBookedSeats().get(i).getPrice();
        }
        return new Payment(userBooking, charges);
    }

    private static void printAvailableSeats(List<Seat> seats) {
        for(int i=0;i<seats.size();i++) {
            System.out.println(seats.get(i).getRowId() + " " + (seats.get(i).getSeatNo()));
        }
    }
}
