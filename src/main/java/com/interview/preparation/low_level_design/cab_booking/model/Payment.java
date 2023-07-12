package com.interview.preparation.low_level_design.cab_booking.model;

import com.sun.security.auth.UnixNumericUserPrincipal;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Payment {
    private String paymentId;
    private Trip trip;

    public Payment(Trip trip){
        this.paymentId = UUID.randomUUID().toString();
        this.trip = trip;
    }
}
