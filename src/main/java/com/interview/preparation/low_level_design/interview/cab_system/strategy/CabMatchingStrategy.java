package com.interview.preparation.low_level_design.interview.cab_system.strategy;

import com.interview.preparation.low_level_design.interview.cab_system.model.Passenger;
import java.util.List;

public interface CabMatchingStrategy {
  List<Integer> findBestCabs(Passenger passenger, List<Integer> cabPositions, int searchRadius);
}
