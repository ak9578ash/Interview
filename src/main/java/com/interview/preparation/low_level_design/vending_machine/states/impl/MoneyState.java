package com.interview.preparation.low_level_design.vending_machine.states.impl;

import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;
import com.interview.preparation.low_level_design.vending_machine.model.Coin;
import com.interview.preparation.low_level_design.vending_machine.model.Item;
import com.interview.preparation.low_level_design.vending_machine.model.VendingMachine;
import com.interview.preparation.low_level_design.vending_machine.states.State;

import java.util.List;

public class MoneyState implements State {
    public MoneyState(){
        System.out.println("Currently Vending machine is in MoneyState");
    }
    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
        throw new BadRequestException("Please insert coins");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        System.out.println("Accepted the coin");
        machine.getCoinList().add(coin);
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        machine.setVendingMachineState(new SelectionState());
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("you need to click on start product selection button first");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("product can not be dispensed in Money state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("you can not get change in Money state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoinList();
    }
}
