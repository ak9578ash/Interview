package com.interview.preparation.low_level_design.interview.dining.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Table {
    private String id;
    private Restaurant restaurant;
    private List<Customer> customer;
    private Integer bookingValidity;
    private LocalDateTime bookingSlot;

    public Table(Restaurant restaurant , List<Customer>customer , Integer bookingValidity , LocalDateTime bookingSlot){
        this.id = UUID.randomUUID().toString();
        this.restaurant = restaurant;
        this.customer = customer;
        this.bookingValidity = bookingValidity;
        this.bookingSlot = bookingSlot;
    }

    public Boolean isBookingValid(){
        return LocalDateTime.now().isAfter(bookingSlot) && LocalDateTime.now().isBefore(bookingSlot.plusHours(bookingValidity));
    }
}

