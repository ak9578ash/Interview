package com.interview.preparation.low_level_design.interview.amazon;

import com.interview.preparation.low_level_design.interview.amazon.device.AudioAlexaDevice;
import com.interview.preparation.low_level_design.interview.amazon.device.AudioScreenAlexaDevice;
import com.interview.preparation.low_level_design.interview.amazon.device.ScreenAlexaDevice;
import com.interview.preparation.low_level_design.interview.amazon.strategy.ChargingStrategy;
import com.interview.preparation.low_level_design.interview.amazon.strategy.DefaultAudioDeviceChargingStrategy;
import com.interview.preparation.low_level_design.interview.amazon.strategy.DefaultAudioScreenDeviceChargingStrategy;
import com.interview.preparation.low_level_design.interview.amazon.strategy.DefaultScreenDeviceChargingStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AmazonMain {
  public static void main(String[] args) {
    ChargingStrategy audioDeviceCHargingStrategy = new DefaultAudioDeviceChargingStrategy();
    ChargingStrategy audioScreenDeviceCHargingStrategy = new DefaultAudioScreenDeviceChargingStrategy();
    ChargingStrategy screenDeviceCHargingStrategy = new DefaultScreenDeviceChargingStrategy();

    ScreenAlexaDevice screenAlexaDevice = new ScreenAlexaDevice(screenDeviceCHargingStrategy);
    AudioAlexaDevice audioAlexaDevice = new AudioAlexaDevice(audioDeviceCHargingStrategy);
    AudioScreenAlexaDevice audioScreenAlexaDevice = new AudioScreenAlexaDevice(audioScreenDeviceCHargingStrategy);

    audioAlexaDevice.chargeDevice();
    audioAlexaDevice.showStats();

    System.out.println("------------------------");

    audioAlexaDevice.unplugDevice();
    audioAlexaDevice.showStats();

    System.out.println("------------------------");

    screenAlexaDevice.showStats();

    System.out.println("-------------------------");

    audioScreenAlexaDevice.showStats();
  }
}
