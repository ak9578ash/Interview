package com.interview.preparation.low_level_design.vending_machine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemShelf {
    private int shelfCode;
    private Item item;
    private boolean isSold;

    public ItemShelf(int shelfCode ){
        this.shelfCode = shelfCode;
        this.isSold = false;
    }
}
