package com.interview.preparation.low_level_design.amazon_locker_management.model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Item {
  private final String id;
  private final String name;
  private final ItemSize itemSize;
  private final Boolean isItemLockerEligible;

  public Item(String name, ItemSize itemSize, Boolean isItemLockerEligible) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.itemSize = itemSize;
    this.isItemLockerEligible = isItemLockerEligible;
  }
}
