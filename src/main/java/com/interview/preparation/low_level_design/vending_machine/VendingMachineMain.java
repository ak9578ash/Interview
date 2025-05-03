package com.interview.preparation.low_level_design.vending_machine;

import com.interview.preparation.low_level_design.vending_machine.model.*;
import com.interview.preparation.low_level_design.vending_machine.states.State;
import java.util.List;

public class VendingMachineMain {
    public static void main(String[] args) throws Exception {
        VendingMachine vendingMachine = new VendingMachine();

        System.out.println("|");
        System.out.println("filling up the inventory");
        System.out.println("|");

        fillUpInventory(vendingMachine);
        displayInventory(vendingMachine);

        System.out.println("|");
        System.out.println("clicking on InsertCoinButton");
        System.out.println("|");

        State vendingState = vendingMachine.getVendingMachineState();
        vendingState.clickOnInsertCoinButton(vendingMachine);

        vendingState = vendingMachine.getVendingMachineState();
        vendingState.insertCoin(vendingMachine, new Coin(CoinType.NICKEL,5));
        vendingState.insertCoin(vendingMachine, new Coin(CoinType.QUARTER,10));

        System.out.println("|");
        System.out.println("clicking on ProductSelectionButton");
        System.out.println("|");

        vendingState.clickOnStartProductSelectionButton(vendingMachine);
        vendingState = vendingMachine.getVendingMachineState();
        vendingState.chooseProduct(vendingMachine, 102);

        displayInventory(vendingMachine);
    }

    private static void fillUpInventory(VendingMachine vendingMachine){
        List<ItemShelf> slots = vendingMachine.getInventory().getInventory();
        System.out.println("Inventory size: " + slots.size());
        for (int i = 0; i < slots.size(); i++) {
            Item newItem = new Item();
            if(i >= 0 && i<3) {
                newItem.setItemType(ItemType.COKE);
                newItem.setPrice(12);
            }else if(i >= 3 && i<5){
                newItem.setItemType(ItemType.PEPSI);
                newItem.setPrice(9);
            }else if(i >=5 && i<7){
                newItem.setItemType(ItemType.JUICE);
                newItem.setPrice(13);
            }else if(i >=7 && i<10){
                newItem.setItemType(ItemType.SODA);
                newItem.setPrice(7);
            }
            slots.get(i).setItem(newItem);
            slots.get(i).setSold(false);
        }

    }

    private static void displayInventory(VendingMachine vendingMachine){
        List<ItemShelf> slots = vendingMachine.getInventory().getInventory();
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isSold()) {
                continue;
            }
            System.out.println("CodeNumber: " + slots.get(i).getShelfCode() +
                    " Item: " + slots.get(i).getItem().getItemType().name() +
                    " Price: " + slots.get(i).getItem().getPrice() +
                    " isAvailable: " + !slots.get(i).isSold());
        }
    }

}
