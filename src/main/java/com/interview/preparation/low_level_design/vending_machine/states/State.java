package com.interview.preparation.low_level_design.vending_machine.states;

import com.interview.preparation.low_level_design.vending_machine.model.Coin;
import com.interview.preparation.low_level_design.vending_machine.model.Item;
import com.interview.preparation.low_level_design.vending_machine.model.VendingMachine;

import java.util.List;

public interface State {
    void clickOnInsertCoinButton(VendingMachine machine) throws Exception;

    void insertCoin(VendingMachine machine, Coin coin) throws Exception;

    void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception;

    void chooseProduct(VendingMachine machine, int codeNumber) throws Exception;

    Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception;

    int getChange(int returnChangeMoney) throws Exception;

    List<Coin> refundFullMoney(VendingMachine machine) throws Exception;
}
