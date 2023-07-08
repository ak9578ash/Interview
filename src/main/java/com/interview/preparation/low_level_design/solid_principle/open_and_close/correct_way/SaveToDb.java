package com.interview.preparation.low_level_design.solid_principle.open_and_close.correct_way;

import com.interview.preparation.low_level_design.solid_principle.open_and_close.model.Invoice;

public class SaveToDb implements InvoiceDao {
    @Override
    public void Save(Invoice invoice) {
        // some logic to save to DB
    }
}
