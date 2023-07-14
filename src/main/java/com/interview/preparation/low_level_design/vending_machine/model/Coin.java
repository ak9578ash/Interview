package com.interview.preparation.low_level_design.vending_machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coin {
    private CoinType coinType;
    private int value;

}
