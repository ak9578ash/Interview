package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Payment {
  private final String id;
  private final Booking booking;
  private final Double charges;

  public Payment(Booking booking, Double charges) {
    this.id = UUID.randomUUID().toString();
    this.booking = booking;
    this.charges = charges;
  }
}
