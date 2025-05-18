package com.interview.preparation.low_level_design.interview.amazon.strategy;

import com.interview.preparation.low_level_design.interview.amazon.Battery;
import com.interview.preparation.low_level_design.interview.amazon.ChargingStatus;

public class DefaultAudioDeviceChargingStrategy implements ChargingStrategy {
    @Override
    public void chargeDevice(Battery battery) {
        System.out.println("Charging Audio Device");
        battery.setChargingStatus(ChargingStatus.CHARGING);
    }
}
