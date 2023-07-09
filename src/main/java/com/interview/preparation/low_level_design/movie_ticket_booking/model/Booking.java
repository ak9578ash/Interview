package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidBookingStatus;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Booking {
    private String id;
    private Show show;
    private List<Seat> bookedSeats;
    private User user;
    private BookingStatus bookingStatus;

    public Booking(Show show, List<Seat> bookedSeats, User user) {
        this.id = UUID.randomUUID().toString();
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public Boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws InvalidBookingStatus {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidBookingStatus("booking status is not valid");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws InvalidBookingStatus {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidBookingStatus("booking status is not valid");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }
}
