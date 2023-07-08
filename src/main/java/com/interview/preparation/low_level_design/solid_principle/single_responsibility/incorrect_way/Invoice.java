package com.interview.preparation.low_level_design.solid_principle.single_responsibility.incorrect_way;

import com.interview.preparation.low_level_design.solid_principle.single_responsibility.models.Marker;

/*
1. Below class is actually holding 3 responsibility or we can say that the Invoice class has
   3 reason to change its implementation which is against he single responsibility principle
2. So we will create 3 separate classes which have only one responsibility
 */
public class Invoice {
    Marker marker;
    Integer qty;

    public Invoice(Marker marker , Integer qty){
        this.marker = marker ;
        this.qty = qty;
    }

    public Integer calculatePrice(){
        return this.qty * this.marker.getPrice();
    }

    public void printBill(){
        // printing bill
    }

    public void insertIntoDb(){
        // some logic to insert into DB
    }
}
