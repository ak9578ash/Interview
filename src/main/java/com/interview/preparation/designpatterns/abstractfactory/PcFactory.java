package com.interview.preparation.designpatterns.abstractfactory;

public class PcFactory implements ComputerAbstractFactory {
    @Override
    public Computer createComputer() {
        return new PC();
    }
}
