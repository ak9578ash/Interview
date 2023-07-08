package com.interview.preparation.low_level_design.ecomm_product_review.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
}
