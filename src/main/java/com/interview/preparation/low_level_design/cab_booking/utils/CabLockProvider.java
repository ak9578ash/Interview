package com.interview.preparation.low_level_design.cab_booking.utils;

import com.interview.preparation.low_level_design.cab_booking.exception.CabTemporarilyUnavailable;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;

import java.util.List;

public interface CabLockProvider {
    void lockCabs(List<Cab> cabs , String userId)throws CabTemporarilyUnavailable;
    void unlockCabs(List<Cab>cabs , String userId);
    Boolean validateLock(Cab cab , String userId);
    List<Cab>getLockedCabs();
}
