package com.interview.preparation.low_level_design.interview.cab_system.service;


import com.interview.preparation.low_level_design.interview.cab_system.model.Cab;
import com.interview.preparation.low_level_design.interview.cab_system.model.CabStatus;
import com.interview.preparation.low_level_design.interview.cab_system.model.Passenger;
import com.interview.preparation.low_level_design.interview.cab_system.strategy.CabMatchingStrategy;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RideService {
  private final CabService cabService;
  private final CabMatchingStrategy cabMatchingStrategy;
  private static final int FARE_PER_KM = 10;

  public Cab bookRide(Passenger passenger, int searchRadius) {
    List<Integer> cabsPositions = cabService.getAllCabsPositions();
    List<Integer> potentialNearestCabs = cabMatchingStrategy.findBestCabs(passenger, cabsPositions, searchRadius);

    for (int i = 0; i < potentialNearestCabs.size(); i++) {
      Integer cabPosition = potentialNearestCabs.get(i);
      Map<Integer, List<Cab>> cabPositionToCabsMap = cabService.getAllCabPositionToCabsMap();
      List<Cab> cabs = cabPositionToCabsMap.get(cabPosition);

      for(Cab cab: cabs) {
        if (cab.getCabStatus() == CabStatus.AVAILABLE) {
          cabService.removeCab(cab);
          cab.setCabStatus(CabStatus.BOOKED);
          return cab;
        }
      }
    }
    return null;
  }

  public void endRide(Cab cab, int endPosition) {
    cab.setPosition(endPosition);
    cab.setCabStatus(CabStatus.AVAILABLE);
    cabService.addCab(cab);
  }

  public int calculateFare(int startPosition, int endPosition) {
    return (endPosition - startPosition) * FARE_PER_KM;
  }
}
