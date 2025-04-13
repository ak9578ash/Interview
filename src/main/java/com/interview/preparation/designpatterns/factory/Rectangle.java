package com.interview.preparation.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rectangle implements Shape {
  @Override
  public void draw() {
    log.info("Inside Rectangle::draw() method.");
  }

  @Override
  public void getArea() {

  }
}
