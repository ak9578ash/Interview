package com.interview.preparation.low_level_design.interview.dining.service.impl;

import com.interview.preparation.low_level_design.interview.dining.exception.InvalidBookingException;
import com.interview.preparation.low_level_design.interview.dining.model.Customer;
import com.interview.preparation.low_level_design.interview.dining.model.Restaurant;
import com.interview.preparation.low_level_design.interview.dining.model.Table;
import com.interview.preparation.low_level_design.interview.dining.repository.TableBookingRepository;
import com.interview.preparation.low_level_design.interview.dining.service.TableBookingService;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public class TableBookingServiceImpl implements TableBookingService {
    private final TableBookingRepository tableBookingRepository;

    public TableBookingServiceImpl(TableBookingRepository tableBookingRepository) {
        this.tableBookingRepository = tableBookingRepository;
    }

    @Override
    public Table makeBooking(Restaurant restaurant, List<Customer> guest) throws InvalidBookingException {
        LocalDateTime currTime = LocalDateTime.now();
        if(currTime.isAfter(restaurant.getStartTime()) && currTime.isBefore(restaurant.getEndTime())){
            Table table = new Table(restaurant , guest,1,currTime);
            return tableBookingRepository.registerBooking(table);
        }
        throw new InvalidBookingException("booking cannot be done for this restaurant");
    }
}
