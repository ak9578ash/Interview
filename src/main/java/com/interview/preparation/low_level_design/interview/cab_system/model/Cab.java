package com.interview.preparation.low_level_design.interview.cab_system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cab {
  String id;
  String licenseNumber;
  int position;
  CabStatus cabStatus;

  public Cab(String id, String licenseNumber, int position) {
    this.id = id;
    this.licenseNumber = licenseNumber;
    this.position = position;
    this.cabStatus = CabStatus.AVAILABLE;
  }

}
