package com.interview.preparation.low_level_design.movie_ticket_booking.service;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.Seat;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;
import com.interview.preparation.low_level_design.movie_ticket_booking.utils.SeatLockProvider;

import java.util.ArrayList;
import java.util.List;

public class SeatAvailabilityService {
    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public SeatAvailabilityService(BookingService bookingService, SeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getUnavailableSeatsOfShow(Show show) {
        List<Seat> bookedSeat = bookingService.getBookedSeats(show);
        List<Seat> lockedSeats = seatLockProvider.getLockedSeats(show);
        List<Seat> unavailableSeats = new ArrayList<>();
        unavailableSeats.addAll(bookedSeat);
        unavailableSeats.addAll(lockedSeats);

        return unavailableSeats;
    }

    public List<Seat> getAvailableSeatsOfShow(Show show) {
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unAvailableSeats = getUnavailableSeatsOfShow(show);

        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unAvailableSeats);
        return availableSeats;
    }
}
