package com.interview.preparation.low_level_design.interview.dining.service;

import com.interview.preparation.low_level_design.interview.dining.exception.InvalidBookingException;
import com.interview.preparation.low_level_design.interview.dining.model.Customer;
import com.interview.preparation.low_level_design.interview.dining.model.Restaurant;
import com.interview.preparation.low_level_design.interview.dining.model.Table;

import java.util.List;

public interface TableBookingService {
    Table makeBooking(Restaurant restaurant , List<Customer> guest) throws InvalidBookingException;
}
