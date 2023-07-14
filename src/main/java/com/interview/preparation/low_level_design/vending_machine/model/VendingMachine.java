package com.interview.preparation.low_level_design.vending_machine.model;

import com.interview.preparation.low_level_design.vending_machine.states.State;
import com.interview.preparation.low_level_design.vending_machine.states.impl.IdleState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VendingMachine {
    private State vendingMachineState;
    private Inventory inventory;
    private List<Coin> coinList;

    public VendingMachine(){
        this.vendingMachineState = new IdleState();
        this.inventory = new Inventory(10);
        this.coinList = new ArrayList<>();
    }
}
