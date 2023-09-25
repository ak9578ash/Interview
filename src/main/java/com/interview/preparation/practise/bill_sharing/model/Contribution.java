package com.interview.preparation.practise.bill_sharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contribution{
    private final String id;
    private final String description;
    private final Double amount;

}
