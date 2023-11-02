package com.interview.preparation.low_level_design.interview.e_commerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product  {
    private String id;
    private String name;
    private Double price;
    private String brand;
    private CategoryType categoryType;
    private Integer sponsoredWeight;


    public Product(String name , Double price , String brand , CategoryType categoryType){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.categoryType = categoryType;
        this.sponsoredWeight = 0;
    }
}

