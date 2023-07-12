package com.interview.preparation.low_level_design.cab_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CabLock {
    private Cab cab;
    private Integer timeoutInSecond;
    private Date lockTime;
    private String lockedBy;

    public Boolean isLockExpired(){
        final Instant lockInstant = this.lockTime.toInstant().plusSeconds(timeoutInSecond);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

}
