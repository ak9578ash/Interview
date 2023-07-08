package com.interview.preparation.low_level_design.solid_principle.single_responsibility.correct_way;

import com.interview.preparation.low_level_design.solid_principle.single_responsibility.models.Marker;

public class InvoicePrint {
    private Marker marker;
    private Integer qty;

    public InvoicePrint(Marker marker  , Integer qty){
        this.marker = marker;
        this.qty = qty;
    }

    public void printInvoice(){
        // some logic to print the invoice
    }
}
