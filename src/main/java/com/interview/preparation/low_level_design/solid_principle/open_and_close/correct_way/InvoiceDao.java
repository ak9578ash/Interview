package com.interview.preparation.low_level_design.solid_principle.open_and_close.correct_way;

import com.interview.preparation.low_level_design.solid_principle.open_and_close.model.Invoice;

public interface InvoiceDao {
    void Save(Invoice invoice);

}
