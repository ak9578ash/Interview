package com.interview.preparation.low_level_design.cab_booking.service;

import com.interview.preparation.low_level_design.cab_booking.exception.CabNotFoundException;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Trip;
import com.interview.preparation.low_level_design.cab_booking.repository.CabRepository;

import java.util.List;

public class CabService {
    private final CabRepository cabRepository;

    public CabService(CabRepository cabRepository) {
        this.cabRepository = cabRepository;
    }

    public Cab addCab(Cab cab) {
        return cabRepository.addCab(cab);
    }

    public Cab getCabByCabId(String cabId) throws CabNotFoundException {
        return cabRepository.getCabByCabId(cabId);
    }

    public List<Cab> getAllCabs(){
        return cabRepository.getAllCabs();
    }


}
