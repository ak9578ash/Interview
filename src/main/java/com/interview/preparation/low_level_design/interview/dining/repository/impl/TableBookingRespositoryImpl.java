package com.interview.preparation.low_level_design.interview.dining.repository.impl;

import com.interview.preparation.low_level_design.interview.dining.model.Table;
import com.interview.preparation.low_level_design.interview.dining.repository.TableBookingRepository;
import com.interview.preparation.low_level_design.interview.dining.service.TableBookingService;

import java.util.ArrayList;
import java.util.List;

public class TableBookingRespositoryImpl implements TableBookingRepository {
    private static List<Table> tableList = new ArrayList<>();
    @Override
    public Table registerBooking(Table table) {
        tableList.add(table);
        return table;
    }
}
