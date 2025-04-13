package com.interview.preparation.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Square implements Shape {
  @Override
  public void draw() {
    log.info("Inside Square::draw() method.");
  }

  @Override
  public void getArea() {

  }
}
