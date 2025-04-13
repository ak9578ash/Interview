package com.interview.preparation.designpatterns.abstractfactory;

public class Demo {
  public static void main(String[] args) {
    Computer pc = ComputerFactory.getComputer(new PcFactory());
    Computer server = ComputerFactory.getComputer(new ServerFactory());
    System.out.println("AbstractFactory PC Config::"+pc);
    System.out.println("AbstractFactory Server Config::"+server);
  }
}
