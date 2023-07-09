package com.interview.preparation.low_level_design.movie_ticket_booking.utils;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.SeatTemporarilyUnavailableException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Seat;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, String user) throws SeatTemporarilyUnavailableException;
    void unlockSeats(Show show, List<Seat> seats, String userId);
    boolean validateLock(Show show, Seat seat, String userId); // to check whether the seats are locked or not
    List<Seat> getLockedSeats(Show show);
}
