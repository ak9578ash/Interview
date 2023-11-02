package com.interview.preparation.low_level_design.interview.dining.repository;

import com.interview.preparation.low_level_design.interview.dining.model.Table;


public interface TableBookingRepository {
    Table registerBooking(Table table);
}
