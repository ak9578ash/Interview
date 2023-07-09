package com.interview.preparation.low_level_design.movie_ticket_booking.service;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.BadRequestException;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidBookingStatus;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.SeatTemporarilyUnavailableException;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.SeatsPermanentlyUnavailableException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Booking;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Seat;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import com.interview.preparation.low_level_design.movie_ticket_booking.repository.BookingRepository;
import com.interview.preparation.low_level_design.movie_ticket_booking.utils.SeatLockProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatLockProvider seatLockProvider;
    public BookingService(BookingRepository bookingRepository, SeatLockProvider seatLockProvider){
        this.bookingRepository = bookingRepository;
        this.seatLockProvider = seatLockProvider;
    }

    public Booking addBooking(User user, Show show , List<Seat> seats) throws SeatsPermanentlyUnavailableException, SeatTemporarilyUnavailableException {
        if(isAnySeatAlreadyBooked(show,seats)){
            throw new SeatsPermanentlyUnavailableException("seats are permanently booked");
        }
        seatLockProvider.lockSeats(show , seats , user.getId());
        Booking booking = new Booking(show,seats,user);
        return bookingRepository.addBooking(booking);
    }

    public Booking getBookingById(String bookingId){
        return bookingRepository.getBookingById(bookingId);
    }

    public void confirmBooking(final Booking booking, final User user) throws InvalidBookingStatus, BadRequestException {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }

        for (Seat seat : booking.getBookedSeats()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user.getId())) {
                throw new BadRequestException();
            }
        }
        booking.confirmBooking();
    }

    public List<Booking> getAllBookings(final Show show) {
        List<Booking> allBooking = bookingRepository.getAllBooking();
        List<Booking> allBookingOfShow = new ArrayList<>();
        for (Booking booking : allBooking) {
            if (booking.getShow().equals(show)) {
                allBookingOfShow.add(booking);
            }
        }
        return allBookingOfShow;
    }

    public List<Seat> getBookedSeats(final Show show) {
        return getAllBookings(show)
                .stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getBookedSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Boolean isAnySeatAlreadyBooked(Show show , List<Seat>seats){
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }
}
