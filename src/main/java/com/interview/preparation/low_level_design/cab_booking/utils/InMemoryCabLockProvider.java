package com.interview.preparation.low_level_design.cab_booking.utils;

import com.interview.preparation.low_level_design.cab_booking.exception.CabTemporarilyUnavailable;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.CabLock;

import java.util.*;

public class InMemoryCabLockProvider implements CabLockProvider{
    private final Map<Cab , CabLock> cabLockMap;
    private final Integer lockOutTime;

    public InMemoryCabLockProvider(Integer lockOutTime){
        this.cabLockMap = new HashMap<>();
        this.lockOutTime = lockOutTime;
    }
    @Override
    synchronized public void lockCabs(List<Cab> cabs, String userId) throws CabTemporarilyUnavailable {
        for(Cab cab : cabs){
            if(isCabLocked(cab)){
                throw new CabTemporarilyUnavailable("cab is not present at the moment");
            }
        }
        for(Cab cab : cabs){
            lockCab(cab,lockOutTime,userId);
        }
    }

    @Override
    public void unlockCabs(List<Cab> cabs, String userId) {
        for(Cab cab : cabs){
            if(validateLock(cab , userId)){
                unlockCab(cab);
            }
        }
    }

    @Override
    public Boolean validateLock(Cab cab, String userId) {
        return isCabLocked(cab) && cabLockMap.get(cab).getLockedBy().equals(userId);
    }

    @Override
    public List<Cab> getLockedCabs() {
        final List<Cab> lockedCabs = new ArrayList<>();
        for(Cab cab : cabLockMap.keySet()){
            if(isCabLocked(cab)){
                lockedCabs.add(cab);
            }
        }
        return lockedCabs;
    }

    private Boolean isCabLocked(Cab cab){
        return cabLockMap.containsKey(cab) && !cabLockMap.get(cab).isLockExpired();
    }

    private void lockCab(Cab cab , Integer lockOutTime , String userId){
        CabLock cabLock = new CabLock(cab , lockOutTime , new Date(),userId);
        cabLockMap.putIfAbsent(cab ,cabLock);
    }

    private void unlockCab(Cab cab){
        if(!cabLockMap.containsKey(cab)){
            return;
        }
        cabLockMap.remove(cab);
    }
}
