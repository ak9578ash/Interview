package com.interview.preparation.low_level_design.interview.amazon.strategy;

import com.interview.preparation.low_level_design.interview.amazon.Battery;
import com.interview.preparation.low_level_design.interview.amazon.ChargingStatus;

public class DefaultScreenDeviceChargingStrategy implements ChargingStrategy {
    @Override
    public void chargeDevice(Battery battery) {
        // Logic to charge the device
        System.out.println("Charging screen device...");
        battery.setChargingStatus(ChargingStatus.CHARGING);
    }
}
