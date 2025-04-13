package com.interview.preparation.designpatterns.abstractfactory;

public class ComputerFactory {
  public static Computer getComputer(ComputerAbstractFactory factory) {
    return factory.createComputer();
  }
}
