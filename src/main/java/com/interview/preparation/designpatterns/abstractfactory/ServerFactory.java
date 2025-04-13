package com.interview.preparation.designpatterns.abstractfactory;

public class ServerFactory implements ComputerAbstractFactory {
    @Override
    public Computer createComputer() {
        return new Server();
    }
}
