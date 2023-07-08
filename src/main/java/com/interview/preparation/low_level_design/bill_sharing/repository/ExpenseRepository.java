package com.interview.preparation.low_level_design.bill_sharing.repository;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExpenseRepository {
    public static Map<String, Expense> expenseMap = new HashMap<>();
}
