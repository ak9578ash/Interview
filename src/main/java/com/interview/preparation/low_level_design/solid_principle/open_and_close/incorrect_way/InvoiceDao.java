package com.interview.preparation.low_level_design.solid_principle.open_and_close.incorrect_way;

import com.interview.preparation.low_level_design.solid_principle.open_and_close.model.Invoice;
import com.interview.preparation.low_level_design.solid_principle.open_and_close.model.Marker;
/*
This class previously only has saveIntoDB() method, but we modify the class and added the saveToGoogleDrive() method
which is against the open-close principle because the InvoiceDoa class was already tested so we should not modify the
existing tested class
 */
public class InvoiceDao {
    private Invoice invoice;

    public InvoiceDao(Invoice invoice){
        this.invoice = invoice;

    }

    public void saveIntoDB(){
        // some logic to insert the invoice into DB
    }

//    public void saveToGoogleDrive(){
//        // some logic to save to the invoice to google drive
//    }
}
