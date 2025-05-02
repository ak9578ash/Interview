package com.interview.preparation.low_level_design.movie_ticket_booking.repository;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.Booking;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    private static Map<String , Payment> paymentMap = new HashMap<>();
    private static Map<Booking, Integer> bookingFailureMap = new HashMap<>();

    public Payment makePayment(Booking booking ,Payment payment){
        paymentMap.putIfAbsent(booking.getId(), payment);
        return payment;
    }

    public Payment getPaymentByBookingId(String bookingId){
        return paymentMap.get(bookingId);
    }

    public void addToFailureBooking(Booking booking){
        if(!bookingFailureMap.containsKey(booking)){
            bookingFailureMap.put(booking,0);
        }
        Integer initialCount = bookingFailureMap.get(booking);
        Integer finalCount = initialCount + 1;
        bookingFailureMap.put(booking,finalCount);

    }
    public Integer getBookingFailureCount(Booking booking){
        return bookingFailureMap.get(booking);
    }

}
