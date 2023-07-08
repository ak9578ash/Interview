package com.interview.preparation.low_level_design.solid_principle.open_and_close.model;

public class Invoice {
    private Marker marker;
    private Integer qty;

    public Invoice(Marker marker , Integer qty){
        this.marker = marker;
        this.qty = qty;
    }

    // getter and setter
}
