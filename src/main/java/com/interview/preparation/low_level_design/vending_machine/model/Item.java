package com.interview.preparation.low_level_design.vending_machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private ItemType itemType;
    private int price;
}
