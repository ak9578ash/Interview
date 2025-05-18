package com.interview.preparation.low_level_design.interview.amazon.device;

import com.interview.preparation.low_level_design.interview.amazon.Battery;
import com.interview.preparation.low_level_design.interview.amazon.BatteryStatus;
import com.interview.preparation.low_level_design.interview.amazon.ChargingStatus;
import com.interview.preparation.low_level_design.interview.amazon.strategy.ChargingStrategy;

public class AudioScreenAlexaDevice extends AlexaDevice{
  public AudioScreenAlexaDevice(ChargingStrategy chargingStrategy) {
    super("AudioScreenAlexaDevice", new Battery(BatteryStatus.AVAILABLE, ChargingStatus.NOT_CHARGING, 30.0), chargingStrategy);
  }

  @Override
  public void showStats() {
    super.showStats();
  }
}
