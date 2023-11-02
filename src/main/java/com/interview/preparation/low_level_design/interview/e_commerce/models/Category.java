package com.interview.preparation.low_level_design.interview.e_commerce.models;

import lombok.Getter;

@Getter
public class Category {
    private CategoryType categoryType;

    public Category(CategoryType categoryType){
        this.categoryType = categoryType;
    }
}
