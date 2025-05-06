package com.interview.preparation.low_level_design.interview.cab_system.service;

import com.interview.preparation.low_level_design.interview.cab_system.model.Cab;
import java.util.*;

public class CabService {
  private final LinkedList<Integer> cabPositions;
  private final Set<Integer> cabPositionSet; // to maintain uniqueness in cabPositions
  private final Map<Integer, List<Cab>> cabPositionToCabsMap;

  public CabService() {
    this.cabPositionSet = new HashSet<>();
    this.cabPositions = new LinkedList<>();
    this.cabPositionToCabsMap = new HashMap<>();
  }

  public int lowerBound(LinkedList<Integer> list, int key) {
    int low = 0;
    int high = list.size() - 1;
    int ans = list.size();

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (list.get(mid) < key) {
        low = mid + 1;
      } else {
        ans = mid;
        high = mid - 1;
      }
    }
    return ans;
  }

  public void addCab(Cab cab) {
    int cabPosition = cab.getPosition();

    if (cabPositionToCabsMap.containsKey(cabPosition)) {
      cabPositionToCabsMap.get(cabPosition).add(cab);
    } else {
      List<Cab> cabs = new ArrayList<>();
      cabs.add(cab);
      cabPositionToCabsMap.put(cabPosition, cabs);
    }

    if (cabPositions.isEmpty()) {
      cabPositions.add(cabPosition);
    } else {
      if (!cabPositionSet.contains(cabPosition)) {
        int lowerBoundIndex = lowerBound(cabPositions, cabPosition);
        cabPositions.add(lowerBoundIndex, cabPosition);
        cabPositionSet.add(cabPosition);
      }
    }
  }

  public List<Integer> getAllCabsPositions() { // should not use getter because these values will be fetched from DAO layer
    return this.cabPositions;
  }

  public Map<Integer, List<Cab>> getCabPositionToCabsMap() {
    return this.cabPositionToCabsMap;
  }
}
