package com.interview.preparation.low_level_design.interview.amazon.strategy;

import com.interview.preparation.low_level_design.interview.amazon.Battery;

public interface ChargingStrategy {
  void chargeDevice(Battery battery);
}
