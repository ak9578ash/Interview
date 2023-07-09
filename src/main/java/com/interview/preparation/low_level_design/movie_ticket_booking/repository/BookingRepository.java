package com.interview.preparation.low_level_design.movie_ticket_booking.repository;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.Booking;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.BookingStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository {
    private static Map<String, Booking> bookingMap = new HashMap<>();
    private static List<Booking> bookingList = new ArrayList<>();


    public Booking addBooking(Booking booking) {
        bookingMap.putIfAbsent(booking.getId(), booking);
        return booking;
    }

    public List<Booking> getAllBooking(){
        return bookingList;
    }

    public Booking getBookingById(String bookingId){
        return bookingMap.get(bookingId);
    }

}
