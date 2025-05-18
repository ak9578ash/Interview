package com.interview.preparation.low_level_design.interview.amazon.device;

import com.interview.preparation.low_level_design.interview.amazon.Battery;
import com.interview.preparation.low_level_design.interview.amazon.BatteryStatus;
import com.interview.preparation.low_level_design.interview.amazon.ChargingStatus;
import com.interview.preparation.low_level_design.interview.amazon.strategy.ChargingStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class AlexaDevice {
  private final String name;
  private final Battery battery;
  private final ChargingStrategy chargingStrategy;

  protected void showStats() {
    System.out.println("Device Name: " + name);
    if (battery.getBatteryStatus() == BatteryStatus.AVAILABLE) {
      System.out.println("Battery is available");
      System.out.println(battery.getCurrentPercentage());
    } else {
      System.out.println("Battery is not available");
    }

    if (battery.getChargingStatus() == ChargingStatus.CHARGING) {
      System.out.println("Battery is charging");
    } else {
      System.out.println("Battery is not charging");
    }
  }

  protected void chargeDevice() {
    chargingStrategy.chargeDevice(battery);
  }

  protected void unplugDevice() {
    battery.setChargingStatus(ChargingStatus.NOT_CHARGING);
    battery.setCurrentPercentage(100.0);
  }
}
