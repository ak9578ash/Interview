package com.interview.preparation.low_level_design.vending_machine.states.impl;

import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;
import com.interview.preparation.low_level_design.vending_machine.model.Coin;
import com.interview.preparation.low_level_design.vending_machine.model.Item;
import com.interview.preparation.low_level_design.vending_machine.model.VendingMachine;
import com.interview.preparation.low_level_design.vending_machine.states.State;

import java.util.ArrayList;
import java.util.List;

public class IdleState implements State {
    public IdleState(){
        System.out.println("Currently Vending machine is in IdleState");
    }

    public IdleState(VendingMachine machine){
        machine.setVendingMachineState(new IdleState());
        machine.setCoinList(new ArrayList<>());
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
        machine.setVendingMachineState(new MoneyState());
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new BadRequestException("you can not insert Coin in idle state");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        throw new BadRequestException("first you need to click on insert coin button");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new BadRequestException("you can not choose Product in idle state");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new BadRequestException("product can not be dispensed idle state");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        throw new BadRequestException("you can not get change in idle state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        throw new BadRequestException("you can not get refunded in idle state");
    }
}
