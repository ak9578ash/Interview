package com.interview.preparation.low_level_design.interview.dining.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String firstLine;
    private String secondLine;
    private String state;
    private String city;
    private String pinCode;
}
