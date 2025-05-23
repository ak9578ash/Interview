package com.interview.preparation.low_level_design.vending_machine.model;

import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    private List<ItemShelf> inventory;
    private int itemCount;

    public Inventory(int itemCount) {
        this.itemCount = itemCount;
        this.inventory = new ArrayList<>();
        initializeShelf();
    }

    private void initializeShelf() {
        int startCode = 101;
        for (int i = 0; i < itemCount; i++) {
            ItemShelf itemShelf = new ItemShelf(startCode);
            inventory.add(itemShelf);
            startCode++;
        }
    }

    public Item addItem(Item item, int shelfCode) throws BadRequestException {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getShelfCode() == shelfCode) {
                if (itemShelf.isSold()) {
                    itemShelf.setItem(item);
                    itemShelf.setSold(false);
                } else {
                    throw new BadRequestException(String.format("item cannot be added at this %d shelf code", shelfCode));
                }
            }
        }
        return item;
    }

    public Item getItem(int shelfCode) throws BadRequestException {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getShelfCode() == shelfCode) {
                if (!itemShelf.isSold()) {
                    return itemShelf.getItem();
                } else {
                    throw new BadRequestException("item already sold out ");
                }
            }
        }
        throw new BadRequestException("invalid code");
    }

    public void updateItemSoldOut(int shelfCode) throws BadRequestException {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getShelfCode() == shelfCode) {
                itemShelf.setSold(true);
                return;
            }
        }
        throw new BadRequestException("shelf code is invalid");
    }
}
