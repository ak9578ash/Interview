package com.interview.preparation.low_level_design.solid_principle.single_responsibility.correct_way;

import com.interview.preparation.low_level_design.solid_principle.single_responsibility.models.Marker;

public class InvoiceBill {
    private Marker marker;
    private Integer qty;
    public InvoiceBill(Marker marker , Integer qty){
        this.marker = marker;
        this.qty = qty;
    }

    public Integer generateBill(){
        return this.marker.getPrice() * this.qty;
    }
}
