package com.interview.preparation.low_level_design.solid_principle.single_responsibility.correct_way;

import com.interview.preparation.low_level_design.solid_principle.single_responsibility.models.Marker;

public class InvoiceDao {
    private Marker marker;

    public InvoiceDao(Marker marker){
        this.marker = marker;
    }

    public void insertInvoiceToDb(){
        // some code to insert to DB
    }
}
