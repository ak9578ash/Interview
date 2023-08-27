package com.interview.preparation.low_level_design.bill_sharing.utils.model;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PercentageSplit {
     User user;
     Expense expense;
     Double percentage;
}
