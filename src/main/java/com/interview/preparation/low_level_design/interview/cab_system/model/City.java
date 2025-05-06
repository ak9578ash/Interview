package com.interview.preparation.low_level_design.interview.cab_system.model;

import java.util.List;

class City {
  String id;
  String name;
  List<Integer> cityPoints;


  public City(String id, String name, List<Integer>cityPoints) {
    this.id = id;
    this.name = name;
    this.cityPoints = cityPoints;
  }
}
