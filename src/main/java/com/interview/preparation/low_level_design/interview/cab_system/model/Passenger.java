package com.interview.preparation.low_level_design.interview.cab_system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Passenger{
  String id;
  String name;
  int position;

  public Passenger(String id, String name, int position) {
    this.id = id;
    this.name = name;
    this.position = position;
  }
}
