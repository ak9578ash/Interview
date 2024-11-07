package com.interview.preparation.low_level_design.interview.wayfair;

class Category {
    private final String categoryName;
    private final String categoryParentName;

    public Category(String categoryName, String categoryParentName) {
        this.categoryName = categoryName;
        this.categoryParentName = categoryParentName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getCategoryParentName() {
        return this.categoryParentName;
    }
}
