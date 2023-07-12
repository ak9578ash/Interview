package com.interview.preparation.low_level_design.cab_booking.repository;

import com.interview.preparation.low_level_design.cab_booking.exception.CabNotFoundException;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabRepository {
    public static Map<String , Cab> cabMap = new HashMap<>();
    public static List<Cab> cabList = new ArrayList<>();

    public Cab addCab(Cab cab){
        cabMap.putIfAbsent(cab.getId() , cab);
        cabList.add(cab);
        return cab;
    }

    public Cab getCabByCabId(String cabId) throws CabNotFoundException {
        Cab cab  = cabMap.get(cabId);
        if(cab==null){
            throw new CabNotFoundException("cab does not exist");
        }
        return cab;
    }

    public List<Cab> getAllCabs(){
        return cabList;
    }

}
