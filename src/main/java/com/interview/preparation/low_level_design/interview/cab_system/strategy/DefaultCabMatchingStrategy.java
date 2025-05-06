package com.interview.preparation.low_level_design.interview.cab_system.strategy;

import com.interview.preparation.low_level_design.interview.cab_system.model.Passenger;
import java.util.ArrayList;
import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy { // TC: O(logn + k) --> refer this : https://leetcode.com/problems/find-k-closest-elements/description/
  private static final int MAX_CABS_SIZE = 10;

  @Override
  public List<Integer> findBestCabs(Passenger passenger, List<Integer> cabPositions, int searchRadius) {
    int index = closestIndex(cabPositions, passenger.getPosition());
    int l = index;
    int r = index + 1;
    int count = 0;
    List<Integer> potentialCabPositions = new ArrayList<>();

    while (l >= 0 && r < cabPositions.size() && count < MAX_CABS_SIZE) {
      if (Math.abs(cabPositions.get(l) - passenger.getPosition()) <= Math.abs(cabPositions.get(r) - passenger.getPosition())) {
        if (Math.abs(cabPositions.get(l) - passenger.getPosition()) > searchRadius) {
          break;
        }
        potentialCabPositions.add(cabPositions.get(l));
        l--;
      } else {
        if (Math.abs(cabPositions.get(r) - passenger.getPosition()) > searchRadius) {
          break;
        }
        potentialCabPositions.add(cabPositions.get(r));
        r++;
      }
      count++;
    }

    while (count < MAX_CABS_SIZE && l >= 0) {
      if (Math.abs(cabPositions.get(l) - passenger.getPosition()) > searchRadius) {
        break;
      }
      potentialCabPositions.add(cabPositions.get(l));
      l--;
      count++;
    }

    while (count < MAX_CABS_SIZE && r < cabPositions.size()) {
      if (Math.abs(cabPositions.get(r) - passenger.getPosition()) > searchRadius) {
        break;
      }
      potentialCabPositions.add(cabPositions.get(r));
      r++;
      count++;
    }
    return potentialCabPositions;
  }

  private int closestIndex(List<Integer> cabPositions, int passengerPosition) {
    int l = 0;
    int r = cabPositions.size() - 1;
    int ans = -1;
    int diff = Integer.MAX_VALUE;

    while (l <= r) {
      int mid = l + (r - l) / 2;

      if (cabPositions.get(mid) == passengerPosition) {
        return mid;
      }

      int currentDiff = Math.abs(cabPositions.get(mid) - passengerPosition);
      if (currentDiff < diff) {
        diff = currentDiff;
        ans = mid;
      } else if (currentDiff == diff && cabPositions.get(mid) < cabPositions.get(ans)) {
        ans = mid;
      }

      if (cabPositions.get(mid) < passengerPosition) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return ans;
  }
}
