package com.interview.preparation.low_level_design.interview.amazon;

import com.interview.preparation.low_level_design.interview.amazon.strategy.ChargingStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Battery {
    private final BatteryStatus batteryStatus;
    private ChargingStatus chargingStatus;
    private double currentPercentage;
}
